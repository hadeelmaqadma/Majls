package Project;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Trying implements Initializable {
    public TextField text;
    DBModel model= DBModel.getModel();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       // TextFields.bindAutoCompletion(text,model.phone_list()) ;

    }
}
