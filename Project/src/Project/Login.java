package Project;


import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    public TextField id ;
    public PasswordField pword;
    public AnchorPane root;
    private String username ;
    private String Password ;
    DBModel DB;
    public Label msg ;
    Navigation nav = new Navigation();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    DBModel db = DBModel.getModel();
    void USERNAME(){
        username= db.getName(id.getText());
    }
    public void enter(ActionEvent actionEvent){
        if(id.getText().equals("manager")){
            loginasmanager();
        }
        else{
        Password = pword.getText();
        USERNAME();
        if(username != null ) {
            if(db.testConn(username.toLowerCase(),Password)){
                db.closeConn();
              DB= DBModel.getModel(username.toLowerCase(), Password);
              DB.setUserID(id.getText());
              nav.navTo(root,"VolView.fxml");
            }
            else{
                msg.setText("password authentication failed for user " + username );
            }
        }
         else{
             msg.setText("UserName Not Found ##");
        }
        }

    }

    private void loginasmanager() {
        if(db.testConn(id.getText(),pword.getText())){
            db.closeConn();
        DBModel db = DBModel.getModel(id.getText(),pword.getText());
        nav.navTo(root,"MangView.fxml");
        }
        else {
            msg.setText("password authentication failed for Manager");
        }
    }


}
