package Project;
/*
* • إمكانية عرض تقرير الحضور لكل طالب في مجلس معين بسرعة عند طلب الطالب ذلك
أثناء تسجيل حضوره في أحد المحاضرات. مع توفير وسيلة لتعديل الخطأ في التقرير
بسرعة.
* done
* */


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ResourceBundle;
public class ReportCtrl implements Initializable {
    public TableView<Model> table;
    public Label msg;
    DBModel db=DBModel.getModel();
    public TextField text;
    public TextField num;
    public TextField date;
    public TableColumn<Model,String> alter_Mos;
    public TableColumn<Model,String> topic ;
    public TableColumn<Model,String> lec_date ;
    String mjid ;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void transferMj_id(String mj_id) {
       mjid = mj_id;
    }

    public void btn() {

        ResultSet rs =db.bla(num.getText() , mjid);
        showtable(rs);
        try {
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void showtable(ResultSet rs){
        alter_Mos.setCellValueFactory(new PropertyValueFactory<>("alter_Mos"));
        topic.setCellValueFactory(new PropertyValueFactory<>("topic"));
        lec_date.setCellValueFactory(new PropertyValueFactory<>("lec_date"));
        table.setItems(list(rs));
    }

    ObservableList<Model> list(ResultSet rs ){
        ObservableList<Model> oblist = FXCollections.observableArrayList();
        try {
            while( rs.next() ){
                Model Model = new Model(rs.getString(1),rs.getString(2),rs.getString(3));
                 oblist.add(Model);}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return oblist ;
    }

    private String selectedDate=null ;
    public TextField selectedRow ;
    public void clicked(MouseEvent mouseEvent) {
        Model model = table.getSelectionModel().getSelectedItem();
        selectedDate= model.getLec_date() ;
        selectedRow.setText("Delete from "+ model.getTopic() + " ?");
        selectedRow.setEditable(false);
    }
    public void delete(ActionEvent actionEvent) {
        if(selectedDate != null){
            selectedRow.setText(db.updateDelete(num.getText() , selectedDate, mjid));
            table.refresh();
            btn() ;
        }

    }

    public void update(ActionEvent actionEvent) {
        msg.setText("");
        if(!date.getText().equals("")){
           if( db.checkLecture(mjid , date.getText()) ){
              int row= db.InsertAttendance(num.getText() , mjid , date.getText()) ;
              if (row == 1){
                  String topic =db.getTopic(mjid , date.getText());
                  msg.setText("DONE ^^");
                   Model model =new Model(date.getText() , topic , null);
                   table.getItems().add(model) ; }
               else
                   msg.setText("Student Not Registered IN this Majls");

           }
        }
        else{
        msg.setText("Lecture Not Exists"); }
    }



    public class Model{
        String alter_Mos ,lec_date,topic;

        public Model( String lec_date, String topic ,String alter_Mos) {
            this.alter_Mos = alter_Mos;
            this.lec_date = lec_date;
            this.topic = topic;

        }

        public String getAlter_Mos() {
            return alter_Mos;
        }

        public void setAlter_Mos(String alter_Mos) {
            this.alter_Mos = alter_Mos;
        }

        public String getLec_date() {
            return lec_date;
        }

        public void setLec_date(String lec_date) {
            this.lec_date = lec_date;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }


    }
}





