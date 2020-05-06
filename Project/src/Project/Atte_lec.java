package Project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Atte_lec implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    String mjid;
    public void transferMj_id(String mj_id) {
        mjid=mj_id;
    }
    public BorderPane br ;
    public void lec(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddLec.fxml"));
        try {
            Pane root = loader.load();
            AddLec ctrl = loader.getController();
            ctrl.transferMj_id(mjid);
            br.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void att(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Attendance.fxml"));
        try {
            Pane root = loader.load();
            Attendance ctrl = loader.getController();
            ctrl.transferMj_id(mjid);
            br.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
   /* public Pane getView(String Path){
        Pane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(Path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return root;
    }*/

}
