package Project;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class Navigation {

    public void navTo(Parent current_view , String Path_to){
        try {
            Parent root_to = FXMLLoader.load(getClass().getResource(Path_to));
            current_view.getScene().setRoot(root_to);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
