package Project;


import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
/*
*  وتسجيل انتماء طالب معين إلى
م جلس معين، وتسجيل وتحرير حضورهم للمحاضرات
o يجب تسجيل االسم رباعي، ورقم الجوال )أو الجواالت(، ومنطقة السكن لكل
طالب.  Done
* */
public class RegCtrl implements Initializable {
    DBModel DB=DBModel.getModel();
    public Label mjid ;
    public TextField num;
    public String mj_id;
    public void transferMj_id(String Mj_id){
        mj_id =Mj_id;
        mjid.setText("#" + Mj_id);
        mjid.setStyle("-fx-font-size: 22");
    }

   @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public Label msg;
    public void add(ActionEvent actionEvent) {
        msg.setText(DB.Register(num.getText() , mj_id));
        System.out.println("Done");
    }

    public void back(ActionEvent actionEvent) {
        Stage stage = (Stage) msg.getScene().getWindow();
        stage.close();
    }
}
