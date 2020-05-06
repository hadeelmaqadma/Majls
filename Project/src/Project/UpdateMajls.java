package Project;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateMajls implements Initializable {
    public TextField mjid;
    public TextField msq;
    public TextField sub;
    public TextField id;
    public Label msg ;
    DBModel DB= DBModel.getModel();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void update(ActionEvent actionEvent) {
        if(!mjid.getText().equals("")){
           if(DB.checkMajlsPrimaryKey(mjid.getText())){ // Not Exists
               if(id.getText()!=null && !id.getText().equals("") ) {
                   if (DB.CheckVol(id.getText())) {
                       Add();
                   } else msg.setText("Volunteer Does Not Exist !! ");
                   Add();
               }
           }
           else {
               if (sub.getText().equals("") && msq.getText().equals("")) {
                   if (id.getText() == null || id.getText().equals("")) {
                       update();
                   } else if (DB.CheckVol(id.getText())) {
                       update();
                   } else msg.setText("Volunteer Does Not Exist !! ");
               }
               else{
                   if (id.getText() == null || id.getText().equals("")) {
                       update1();
                   } else if (DB.CheckVol(id.getText())) {
                       update1();
                   } else msg.setText("Volunteer Does Not Exist !! ");
               }
           }
        }
        else {
            msg.setText("Majls ID Is Required •••");
        }
    }
    public void update(){
        String s = DB.MajlsVol(mjid.getText(), id.getText());
        msg.setText(s);
    }
    public void update1(){
       String s= DB.updateMajls(mjid.getText(), sub.getText(), msq.getText(),id.getText());
       msg.setText(s);
    }
        public void Add(){
            DB.AddMajls(mjid.getText(), sub.getText(), msq.getText(), id.getText());
        }



}
