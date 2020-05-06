package Project;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VolViewCtrl implements Initializable {
    public ChoiceBox<String> opr;
    public ComboBox<String> mjid;

    public String getMj_id() {
        return Mj_id;
    }

    public void setMj_id(String mj_id) {
        Mj_id = mj_id;
    }

    public String Mj_id =null;

    DBModel db = DBModel.getModel();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setComboBoxes();
    }
    private void setComboBoxes() {
        ObservableList<String> mjs = FXCollections.observableList(db.mjids(db.getUserID()));
        ArrayList<String> choices = new ArrayList<>();
        choices.add("Register"); // done
        choices.add("Attendance/AddLecture"); //done
        choices.add("Student Report"); // done
        choices.add("Import Files"); //done
        choices.add("Lecture Details"); // done
        choices.add("More Data"); // done
        opr.setItems(FXCollections.observableArrayList(choices));
        mjid.setItems(mjs);

    }

    public void next(ActionEvent actionEvent) {
        setMj_id(mjid.getValue());
        switch(opr.getValue()){
            case "Register" : loadRegisterView() ;break;
            case "Lecture Details": LoadLecDetails(); break;
            case "More Data" : LoadMoreDataView(); break;
            case "Import Files": LoadImportView();break;
            case "Student Report" : LoadStudentReportView() ; break;
            case "Attendance/AddLecture" :LoadAttendanceView(); break ;
        }

    }

    private void LoadAttendanceView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Atte_lec.fxml"));
        try {
            Parent root = loader.load();
            Atte_lec ctrl = loader.getController();
            ctrl.transferMj_id(getMj_id());
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 600 ,400));
            stage.setTitle("Report File");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void LoadStudentReportView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Report.fxml"));
        try {
            Parent root = loader.load();
            ReportCtrl ctrl = loader.getController();
            ctrl.transferMj_id(getMj_id());
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 490 ,420));
            stage.setTitle("Report File");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void LoadImportView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Import.fxml"));
        try {
            Parent root = loader.load();
            ImportCtrl ctrl = loader.getController();
            ctrl.transferMj_id(getMj_id());
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Import File");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void LoadMoreDataView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Data.fxml"));
        try {
            Parent root = loader.load();
            DataCtrl ctrl = loader.getController();
            ctrl.transferMj_id(getMj_id());
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("More Data");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadRegisterView() {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Reg.fxml"));
        try {
            Parent root = loader.load();
            RegCtrl ctrl = loader.getController();
            ctrl.transferMj_id(getMj_id());
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Register Student");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void LoadLecDetails(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("lecDetails.fxml")) ;

        try {
            Parent root=loader.load();
            lecDetailsCtrl ctrl =loader.getController();
            ctrl.transferMj_id(getMj_id());
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Lecture Details");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    Navigation nav =new Navigation();
    public AnchorPane root;
    public void finish(ActionEvent actionEvent) {
        db.closeConn();
        nav.navTo(root, "Start.fxml");
    }
}
