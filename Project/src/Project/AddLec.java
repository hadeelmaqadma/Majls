package Project;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddLec implements Initializable {
    public Label majls;
    public TextField date;
    public TextField topic;
    public Label msg;
    public TextField msq;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    String mjid;
    public void transferMj_id(String mjid) {
        this.mjid =mjid;
        majls.setText(majls.getText()+ this.mjid);
    }
    DBModel DB = DBModel.getModel();
    public void add(ActionEvent actionEvent) {
        msg.setText("");
        if(!date.getText().equals("") && date.getText() != null){
         if (!DB.checkLecture(mjid , date.getText())){
             int i = DB.insertLec(mjid , date.getText() , topic.getText() ,msq.getText() );
             System.out.println(i);
             if(i ==1 ) msg.setText("DONE");

         }else msg.setText("Lecture ALready Exists ");
        } else {
            msg.setText("Date Cannot be null ^%^");
        }

    }
}
