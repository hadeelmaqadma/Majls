package Project;


import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;

import java.util.ResourceBundle;
/*o في حالة تغيب المتطوع المسؤول عن البرنامج سيقوم متطوع آخر مشكورا
بإدخال أرقام جواالت جميع الحضور في ملف اكسيل،
//Done
* */
public class AlterCtrl implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public TextField mjid;
    public TextField path ;
    public TextField date ;
    public Label msg ;
    DBModel DB= DBModel.getModel();
    String msssg ="";
    public String majls ;

    public void insert(ActionEvent actionEvent) {
        majls=mjid.getText();
        if(DB.checkVaild(date.getText(),majls)) {
            File file = new File(path.getText());
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                msg.setText(DB.addPath(date.getText() , majls, path.getText()));
               // msg.setText(DB.addPath());
            } catch (FileNotFoundException e) {
                msg.setText("FileNotFound");
               // e.printStackTrace();
            }
        }

    }


}
