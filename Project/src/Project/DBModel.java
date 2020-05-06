package Project;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.*;
import java.util.ArrayList;

public class DBModel {
    private Connection conn = null;
    private String username = null;
    private String password = null;
    private String Schema = "MAJLS_Project";
    private static DBModel dbmodel = null;

    public static DBModel getModel() {
        if (dbmodel == null) {
            dbmodel = new DBModel();
        }
        return dbmodel;
    }

    private DBModel() {
        setUsername("manager");
        setPassword("manager");
        SchemaConn();
    }

    public static DBModel getModel(String username, String Password) {
        dbmodel = new DBModel(username, Password);
        return dbmodel;

    }

    private DBModel(String username, String password) {
        this.username = username;
        this.password = password;
        SchemaConn();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public void connect() {
        PGSimpleDataSource DS = new PGSimpleDataSource();
        DS.setServerName("localhost");
        DS.setDatabaseName("Project");
        DS.setUser(getUsername());
        DS.setPassword(getPassword());

        try {
            conn = DS.getConnection();
            //System.out.println("Successful connection as " + getUsername());

        } catch (SQLException throwables) {
          //  System.out.println("password authentication failed for user "+getUsername());
            throwables.printStackTrace();
        }

    }
    public boolean testConn(String username, String password){
        PGSimpleDataSource DS = new PGSimpleDataSource();
        DS.setServerName("localhost");
        DS.setDatabaseName("Project");
        DS.setUser(username);
        DS.setPassword(password);

        try {
            conn = DS.getConnection();
           return true;

        } catch (SQLException throwables) {
           return false ;
          //  throwables.printStackTrace();
        }
    }
    public void SchemaConn() {
        connect();
        try {
            Statement s = conn.createStatement();
            s.execute("set search_path to '" + Schema + "' ;");
          //  System.out.println("Connected to schema " + Schema);
        } catch (SQLException throwables) {
           // System.out.println("Faild");
            throwables.printStackTrace();
        }
    }

    public void closeConn() {
        try {
            conn.close();
            setPassword(null);
            setUsername(null);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    String UserID = null;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getName(String ID) {

        String sql = "Select name from volunteer where ID= '" + ID + "' ;";
        try (Statement s = conn.createStatement(); ResultSet rs = s.executeQuery(sql)) {
            while (rs.next())
                return rs.getString(1);

        } catch (SQLException throwables) {

            //throwables.printStackTrace();
        }
        return null;
    }

    public boolean checkVolName(String name) {
        String sql = "select * from volunteer where name= ? ;";
        try (PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rs.close();
                return true;
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public ArrayList<String> mjids(String volId) {
        String sql = "select Mj_id from majls where vol_id='" + volId + "';";
        ArrayList<String> mjids = new ArrayList<>();
        try (Statement s = conn.createStatement(); ResultSet rs = s.executeQuery(sql)) {
            while (rs.next())
                mjids.add(rs.getString(1));
            return mjids;
        } catch (SQLException throwables) {
            // throwables.printStackTrace();
            return null;
        }
    }

    public String createUser(String user, String PassW) {

        String S = "create user " + user + " with login password '" + PassW + "' ; ";
        try (Statement ps = conn.createStatement()) {
            ps.execute(S);
            String ss = "grant users to " + user + ";";
            ps.execute(ss);
            return "DONE";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "OOPS";
    }

    public String Register(String phone, String mjid) {
        int stuId = getStudentId(phone);
        if (stuId == 0) return "InValid PhoneNumber";
        String sql = "insert into register(ID, Mj_id) values (" + stuId + ", '" + mjid + "');";
        try (Statement s = conn.createStatement();) {
            s.execute(sql);
            return "Registered $$ ";
        } catch (SQLException throwables) {
            return "The Student is already registerd :( ";
            //throwables.printStackTrace();
        }

    }

    public int getStudentId(String phone) {
        String sql = "select ID from phone  where num='" + phone + "';";
        try (Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(sql);) {

            while (rs.next())
                return Integer.parseInt(rs.getString(1));

            return 0;
        } catch (SQLException throwables) {
            return 0;
            //throwables.printStackTrace();
        }
    }

    public ResultSet getCert(String mjid) {

        String s = "select * from cert('" + mjid + "') ;";
        try {
            Statement ss = conn.createStatement();
            ResultSet rs = ss.executeQuery(s);
            return rs;
        } catch (SQLException throwables) {
            System.out.println("not valid");
            return null;
            //  throwables.printStackTrace();
        }
    }

    public ResultSet getAtt(String mjid) {

        String s = "select  ID, name1, name4,topic, lec_date " +
                " from student natural join attendance natural join lecture where mj_id='" + mjid + "' order by name1;";
        try {
            Statement ss = conn.createStatement();
            ResultSet rs = ss.executeQuery(s);
            return rs;
        } catch (SQLException throwables) {
            System.out.println("not valid");
            return null;
            //  throwables.printStackTrace();
        }
    }

    public String mostAttendingLec(String mjid) {
        // Most Attendance ;
        String s = "select lec_date , topic\n" +
                "from attendance a natural join lecture \n" +
                "group by lec_date , topic\n" +
                "having count(distinct id) >= all(select count(distinct id) \n" +
                "                             from attendance a natural join lecture \n" +
                "                             where mj_id='" + mjid + "'\n" +
                "                             group by lec_date ,topic );";
        try {
            Statement ss = conn.createStatement();
            ResultSet rs = ss.executeQuery(s);
            rs.next();
            return "\"" + rs.getString(1) + " \"  " + rs.getString(2);

        } catch (SQLException throwables) {
            System.out.println("Invalid");
            return null;
            //  throwables.printStackTrace();
        }
    }

    public ResultSet getAtt(String mjid, String topic) {

        String s = "select  ID, name1, name4 as name2 " +
                " from student natural join attendance natural join lecture where mj_id='" + mjid +
                "' and topic='" + topic + "' order by name1;";
        try {
            Statement ss = conn.createStatement();
            ResultSet rs = ss.executeQuery(s);
            return rs;
        } catch (SQLException throwables) {
            System.out.println("Invalid");
            return null;
            //  throwables.printStackTrace();
        }
    }

    public String LecDet(String mjid, String topic) {
       /* String sql="select (select count(distinct ID)\n" +
                "        from attendance natural join lecture\n" +
                "        where topic ='Hajj')*100.0 / (select count(distinct ID ) from register r where r.mj_id='FO1' )  as avg;";*/
        String sql1 = "select count(distinct ID)\n" +
                "                 from attendance natural join lecture\n" +
                "                  where topic ='" + topic + "' ;";

        String sql2 = "select count(distinct ID ) from register r where r.mj_id='" + mjid + "' ; ";
        try {
            Statement ss = conn.createStatement();
            ResultSet rs = ss.executeQuery(sql1);
            rs.next();
            int num = Integer.parseInt(rs.getString(1));
            ResultSet rs2 = ss.executeQuery(sql2);
            rs2.next();
            int num2 = Integer.parseInt(rs2.getString(1));
            String s = "";
            s = "Percentage of Attendance : " + ((num * 100.0) / num2) + "% \n"
                    + "Attendance : " + num + "  Absence : " + (num2 - num);
            return s;
        } catch (SQLException throwables) {
            return "Somthing Wrong :(";

            //  throwables.printStackTrace();
        }
    }

    public ArrayList<String> phone_list() {
       // String sql = "select num from phone natural join register where Mj_id ='"+mjid+"' ;";
        String sql = "select distinct num from phone natural join register ;";
        ArrayList<String> list = new ArrayList<>();
        try (Statement s = conn.createStatement(); ResultSet rs = s.executeQuery(sql)) {
            while (rs.next())
                list.add(rs.getString(1));


        } catch (SQLException throwables) {
            //throwables.printStackTrace();
        }
        return list;
    }

    public Boolean checkVaild(String date, String mjid) {
        String sql = "select * from lecture l where lec_date='" + date + "' and mj_id='" + mjid + "' ;";
        try (Statement s = conn.createStatement();
             ResultSet rs = s.executeQuery(sql)) {
            if (rs.next())
                return true;
        } catch (SQLException throwables) {
            // throwables.printStackTrace();

        }
        return false;
    }

    public boolean checkNumber(String num, String mjid) {
        String sql = "select * from phone natural join register where num='" + num + "' and mj_id='" + mjid + "';";
        try (Statement s = conn.createStatement(); ResultSet rs = s.executeQuery(sql)) {
            if (rs.next()) return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public String addPath(String date, String majls, String path) {
        String sql = "insert into file_path values('" + path + "','" + majls + "','" + date + "') ;";
        try (Statement s = conn.createStatement();) {
            s.execute(sql);
            return "File Added Successfully";
        } catch (SQLException throwables) {
            return "File Already Exists";
            // throwables.printStackTrace();
        }
    }

    public String getPath(String mjid, String date) {
        String sql = "select fpath from file_path where mj_id='" + mjid + "' and lec_date='" + date + "' ;";
        // String sql=" select fpath from file_path where mj_id='FO1' and lec_date='2020-05-21' ;" ;
        try (Statement s = conn.createStatement(); ResultSet rs = s.executeQuery(sql)) {
            if (rs.next()) return rs.getString(1);

            return "NO FILES";
        } catch (SQLException throwables) {

            //   throwables.printStackTrace();
            return "NO FILES";

        }
    }

    public int InsertAttendance(String num, String mjid, String date) {
        int id = getStudentId(num);
        String sql = " insert into Attendance values( " + id + " , '" + mjid + "' , '" + date + "') ;";
        try (Statement s = conn.createStatement()) {
            return s.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public ResultSet bla(String num, String mjid) {
        int id = getStudentId(num);
        String sql = "select lec_date,topic ,alter_Mos from attendance a natural join lecture where id=" + id + " and mj_id='" + mjid + "'; ";
        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);
            return rs;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public String updateDelete(String num, String selectedDate, String mjid) {
        int stu_id = getStudentId(num);
        String sql = "Delete from attendance where id=" + stu_id + "and lec_date='" + selectedDate + "' and mj_id='" + mjid + "';";
        try (Statement s = conn.createStatement();) {
            s.execute(sql);
            return "Done";
        } catch (SQLException throwables) {
            // throwables.printStackTrace();
            return "Oops !!!!";
        }
    }

    public boolean checkLecture(String mjid, String text) {
        String sql = "select * from lecture where lec_date='" + text + "' and mj_id='" + mjid + "';";
        try (Statement s = conn.createStatement(); ResultSet rs = s.executeQuery(sql)) {
            if (rs.next()) return true;
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
        }
        return false;
    }

    public String getTopic(String mjid, String text) {
        String sql = "Select topic from lecture where mj_id='" + mjid + "' and lec_date='" + text + "' ;";
        try (Statement s = conn.createStatement(); ResultSet rs = s.executeQuery(sql)) {
            if (rs.next()) return rs.getString(1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public String AddMajls(String mjid, String subject, String mosq, String VolID) {

        String sql = "insert into majls values (? , ? ,? ,?) ; ";

        try (PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, mjid);
            ps.setString(2, subject);
            ps.setString(3, mosq);
            ps.setString(4, VolID);
            int i = ps.executeUpdate();
            if (i == 1) return "Majls Added Successfully #@";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "";
    }

    public boolean checkMajlsPrimaryKey(String mjid) {
        String sql = " select * from majls where Mj_id= ? ;";
        try (PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, mjid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { // mjid exists
                rs.close();
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public String MajlsVol(String mjid, String id) {
        String sql = "update majls set vol_id = ? where mj_id = ? ; ";
        try (PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(2, mjid);
            ps.setString(1, id);
            int i = ps.executeUpdate();
            if (i == 1) return "DONE ";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return " : ";
    }

    public String AddVol(String name, String id) {
        String sql = "insert into volunteer values ( ? , ? ) ;";
        try (PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, id);
            ps.setString(2, name);
            int i = ps.executeUpdate();
            if (i == 1) return "DONE ;";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return " ";
    }

    public boolean CheckVol(String id) {
        String sql = "Select * from Volunteer where id= ? ;";
        try (PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public String updateMajls(String mjid, String sub, String msq, String id) {
        String sql = "update majls set subject=? ,  mosque =? , Vol_id=  ? where mj_id= ? ; ";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, sub);
            ps.setString(2, msq);
            ps.setString(3, id);
            ps.setString(4, mjid);
            if (ps.executeUpdate() == 1)
                return "Done";
        } catch (SQLException throwables) {

            throwables.printStackTrace();
        }
        return "&^%c";

    }

    public int insertLec(String mjid, String date, String sub, String msq) {
      /*  String sql= "insert into lecture values(?, ? , ? ,? ) ; " ;
        try(PreparedStatement ps= conn.prepareStatement(sql);) {
            ps.setString(1,mjid); ps.setString(2,date);
            ps.setString(3,sub); ps.setString(4,msq);
            return ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }*/
        String sql = "insert into lecture values ('" + mjid + "' , '" + date + "' , '" + sub + "' , '" + msq + "' ) ;";
        try (Statement s = conn.createStatement();) {
            return s.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return 0;
    }

   public static void main(String[] args) {
        // DBModel db =new DBModel("manager","manager");
       // db.createUser("kareem","K333");
       // DBModel db2 =new DBModel("kareem","K333");
        /*DBModel db =new DBModel("manager","manager");
        DBModel db2= new DBModel("ahmad" , "A111");
        System.out.println(db.getUsername());
        System.out.println(db2.getPassword());
        /*Successful connection as manager
         Connected to schema MAJLS_Project
         Successful connection as ahmad
         Connected to schema MAJLS_Project
         manager
         A111
        */
        }
}