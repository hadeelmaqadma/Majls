package Project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/*
* مدير النظام الذي يمكنه (إنشاء وتحرير مجالس  +إنشاء وتحرير حسابات مستخدمين + • ربط المتطوع بالمجالس التي يعمل عليها)
* Done ;
 * */
public class MangView implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(8888);
    }
    public BorderPane br;
    Pane view ;
    public void majls() {
        view =getView("UpdateMajls.fxml");
        br.setCenter(view);
    }
    public void createUsers() {
        view =getView("CreatUserView.fxml");
        br.setCenter(view);
    }
    public Pane getView(String Path){
        Pane root = null;
        try {
             root =FXMLLoader.load(getClass().getResource(Path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return root;
    }


}
