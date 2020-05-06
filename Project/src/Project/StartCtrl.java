package Project;


import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class StartCtrl implements Initializable {
    public AnchorPane root;
    Navigation nav =new Navigation ();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void Man(ActionEvent actionEvent) {
         nav.navTo(root, "Login.fxml");
    }

    public void Vol(ActionEvent actionEvent) {
       nav.navTo(root,"Login.fxml");
    }

    public void alter(ActionEvent actionEvent) {
        nav.navTo(root,"Alter.fxml");
    }

    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }
}
