package Project;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreatUserCtrl implements Initializable {
    DBModel DB=DBModel.getModel();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public TextField password;
    public TextField  username;
    public Label msg;
    public void create(ActionEvent actionEvent) {
      if(DB.checkVolName(username.getText())){
         if(checkinput()){
             msg.setText("DONE");
         }
         else{
             msg.setText("This UserName Already Exists");
         }
      }
      else{
        msg.setText("Volunteer Not Exists");
      }

    }
    public boolean checkinput(){
       String s= DB.createUser(username.getText(), password.getText());
      if(s.equals("DONE"))
          return true;
      else
          return false;
    }
}
