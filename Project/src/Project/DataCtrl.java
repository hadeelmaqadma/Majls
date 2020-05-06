package Project;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
/*
* إمكانية استخراج كشف بجميع الطلبة الملتزمين بحضور أكثر من 90 %من المحاضرات
في مجلس علم معين  //Done
* */

/*وكذلك إمكانية تصدير هذا الكشف الى اكسيل لطباعة
      Done  الشهادات
* */

//مكانية عرض جميع المحاضرات المنتمية الى مجلس معين.  done;
public class DataCtrl implements Initializable {
    public ComboBox rep_of ;
    public TableView<String> table;
    public TextField path;
    private String Mjid;
    public void transferMj_id(String Mj_id){
        Mjid=Mj_id;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValues();
    }
    public void setValues(){
        ArrayList<String> list= new ArrayList<>();
        list.add("Certificates");
        list.add("Attendance");
        rep_of.setItems(FXCollections.observableArrayList(list));

    }
    public void show(ActionEvent actionEvent) {
        table.getItems().clear();
        table.getColumns().clear();
        details.setText("");
        switch(rep_of.getValue().toString()){
           case "Certificates" : cert();  break;
           case "Attendance" :att() ; showmore() ;  break;
        }
        try {
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public Label details ;
    public void showmore(){
        if(rep_of.getValue().equals("Attendance")) {
            String s = "Most Attending Lecture :" + db.mostAttendingLec(Mjid) ;
            details.setText(s);
        }
    }
    DBModel db=DBModel.getModel() ;
    ResultSet rs;

    public void cert(){
        rs = db.getCert(Mjid);
        ObservableList dbData = FXCollections.observableArrayList(arrayList1(rs));


        try {
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++) {
                TableColumn column = new TableColumn<>();
                switch (rs.getMetaData().getColumnName(i+1)) {
                    case "id":
                        column.setText("ID #");
                        break;
                    case "name1":
                        column.setText("First Name");
                        break;
                    case "name2":
                        column.setText("Family Name");
                        break;
                    default: column.setText(rs.getMetaData().getColumnName(i+1));
                        break;
                }
                column.setCellValueFactory(new PropertyValueFactory<>(rs.getMetaData().getColumnName(i+1)));
                table.getColumns().add(column);
            }

            table.setItems(dbData);
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void att(){
        rs = db.getAtt(Mjid);
        ObservableList dbData = FXCollections.observableArrayList(arrayList2(rs));
        try {
            for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++) {
                TableColumn column = new TableColumn<>();
                column.setText(rs.getMetaData().getColumnName(i));
                column.setCellValueFactory(new PropertyValueFactory<>(rs.getMetaData().getColumnName(i)));
                table.getColumns().add(column); }
            table.setItems(dbData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }
    private ArrayList<Model> arrayList1(ResultSet rs)  {
        ArrayList<Model> data =  new ArrayList<>();

            try {
               while( rs.next() ){
                Model Model = new Model(rs.getString(1),rs.getString(2),rs.getString(3));

                data.add(Model);}
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        return data;
    }
    private ArrayList<Model2> arrayList2(ResultSet rs) {
        ArrayList<Model2> list =new ArrayList<>();
        while (true){
            try {
                if (!rs.next()) break;
                Model2 model=new Model2(rs.getString(1), rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5));
                list.add(model);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return list;
    }

    public void export(ActionEvent actionEvent) {
        if(!path.getText().equals("")){
            path.setText(exportData());
        }
        else
            path.setText("Enter File Path");
    }
    public String exportData(){
        try (ResultSet  rsExport= db.getCert(Mjid)){

            FileWriter file =new FileWriter(path.getText()) ;
            BufferedWriter br = new BufferedWriter(file);
            br.write("***** Certficates for "+Mjid +" ******");
            br.newLine();
            br.write("ID , First Name ,Family Name");
            while(rsExport.next()){
                br.newLine();
                String line = String.format("%s,%s,%s",
                        rsExport.getString(1), rsExport.getString(2),rsExport.getString(3));
                br.write(line);
            }
            br.close();
            return "DONE $$" ;
        } catch (IOException e) {
            e.printStackTrace();
            return "INCORRECT PATH";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "SORRY ^^ SOMETHING WRONG ^^ " ;
    }

    public class Model {

        String id ;
        String name1;
        String name2;

        public Model(String id, String name1, String name2) {
            this.id = id;
            this.name1 = name1;
            this.name2 = name2;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName1() {
            return name1;
        }

        public void setName1(String name1) {
            this.name1 = name1;
        }

        public String getName2() {
            return name2;
        }

        public void setName2(String name2) {
            this.name2 = name2;
        }
    }
    public class Model2{
        String id, name1, name4,topic, lec_date ;

        public Model2(String id, String name1, String name4, String topic, String lec_date) {
            this.id = id;
            this.name1 = name1;
            this.name4 = name4;
            this.topic = topic;
            this.lec_date = lec_date;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName1() {
            return name1;
        }

        public void setName1(String name1) {
            this.name1 = name1;
        }

        public String getName4() {
            return name4;
        }

        public void setName4(String name4) {
            this.name4 = name4;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getLec_date() {
            return lec_date;
        }

        public void setLec_date(String lec_date) {
            this.lec_date = lec_date;
        }
    }
}
