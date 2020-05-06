package Project;


import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
 /// ونود من البرنامج أن يوفر
 //إمكانية استيراد هذا الملف كحضور لمحاضرة معينة.
// Done ;
public class ImportCtrl implements Initializable {
    public TextField date ;
    public  String mjid;
    public Label msg;
    public TextField path ;
    public void transferMj_id(String mj_id) {
       mjid=mj_id;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    DBModel DB=DBModel.getModel();
    String result;
    public void getpath(ActionEvent actionEvent) {
           result=DB.getPath(mjid , date.getText());

           path.setText(result);

    }
    String mssg="";
    public void insert(ActionEvent actionEvent) {
        if(!result.equals("NO FILES")){
            File file= new File(result);
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String num;
                while((num=br.readLine()) !=null ){
                   if( checkInput("0"+num)){
                      DB.InsertAttendance("0"+num, mjid , date.getText());
                      mssg += num +" : Inserted #  \n" ;
                   }
                    }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
           msg.setText("^%^ I SAID NOFILES");
        }
        msg.setText(mssg);
    }
    public boolean checkInput(String S){

        if(S.length() != 10) {
            mssg +=S + " : InValid Number" +"\n";
            return false;
        }

        if( !DB.checkNumber(S , mjid) ) {
            mssg += S + " : Student Not Registerd" + "\n";
            return false;}

        return true;
    }
}
