package Project;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.ResourceBundle;

public class Attendance  implements Initializable {

    public String mjid;
    DBModel DB= DBModel.getModel();
    public TextField lec_date;
    public Label majls;

    public void transferMj_id(String mj_id) {
        mjid=mj_id;
        majls.setText(majls.getText()+ mj_id);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TextFields.bindAutoCompletion(num,DB.phone_list()) ;
    }
    public String date ;
    public Label msg ;
    public TextField num ;
    public void add(ActionEvent actionEvent) {
        date = lec_date.getText();
        if(DB.checkLecture(mjid, date)){
          int i =DB.InsertAttendance(num.getText() , mjid , date);
          if(i==1 ) msg.setText("DONE");
          else msg.setText("Student Doesn't Register to this Majls ^^ ");
        }else{
            msg.setText("Lecture of Date : "+date+ " Doesnt Exist for "+mjid+ " Majls !");
        }

    }
}
