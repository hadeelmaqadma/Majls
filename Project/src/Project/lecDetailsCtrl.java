package Project;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
// • إمكانية البحث عن كشف محاضرة معينة باستخدام عنوان المحاضرة. 1*
// • إمكانية عرض كشوف الحضور والغياب لكل محاضرة من المحاضرات مع نسبة الحضور وعدد الحضور في كل محاضرة

public class lecDetailsCtrl implements Initializable {
    public String mjid;
    public void transferMj_id(String mj_id) {
        mjid= mj_id ;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public TextField topic ;
    public TableView<String> table;

    public void show(ActionEvent actionEvent) {
        table.getColumns().clear();
        if(!topic.getText().equals("")){
        showtable();
        showLable();
        }
        try {
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    DBModel db=DBModel.getModel() ;
    ResultSet  rs ;
    public void showtable(){
        rs = db.getAtt(mjid , topic.getText());
        ObservableList dbData = FXCollections.observableArrayList(arrayList(rs));
        try {
            for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++) {
                TableColumn column = new TableColumn<>();
                switch (rs.getMetaData().getColumnName(i)) {
                    case "id":
                        column.setText("ID #");
                        break;
                    case "name1":
                        column.setText("First Name");
                        break;
                    case "name2":
                        column.setText("Family Name");
                        break;
                    default: column.setText(rs.getMetaData().getColumnName(i));
                        break; }
                column.setCellValueFactory(new PropertyValueFactory<>(rs.getMetaData().getColumnName(i)));
                table.getColumns().add(column); }

            table.setItems(dbData);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }

    private ArrayList<Model> arrayList(ResultSet rs)  {
        ArrayList<Model> data =  new ArrayList<>();

        try {
            while( rs.next() ){
                Model Model = new Model(rs.getString(1),rs.getString(2),rs.getString(3));

                data.add(Model);}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return data;
    }

    public class Model {

    String id ;
    String name1;
    String name2;

    public Model(String id, String name1, String name2) {
        this.id = id;
        this.name1 = name1;
        this.name2 = name2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }
}

    public Label det_label ;
    public void showLable(){
     det_label.setText(db.LecDet(mjid, topic.getText()));

    }
 }
