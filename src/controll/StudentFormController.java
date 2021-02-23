package controll;/*@author:Dilshan Rajika Withanachchi*/

import business.BOFactory;
import business.custom.impl.StudentBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDTO;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.tm.NotificationMessage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class StudentFormController {
    public AnchorPane root;
    public ImageView lbllogo;
    public JFXTextField txtid;
    public JFXTextField txtname;
    public JFXTextField txtaddress;
    public JFXTextField txtcontactno;
    public JFXDatePicker txtbirthday;
    public JFXComboBox<String> cmbgender;
    public TableView<StudentDTO> tblstudent;
    public TableColumn colno;
    public TableColumn colid;
    public TableColumn colname;
    public TableColumn coladdress;
    public TableColumn colcontact;
    public TableColumn colgender;
    public TableColumn colbirthday;
    public JFXButton btnnext;
    public JFXTextField txtsearch;
    public JFXButton btnsave;
    public JFXButton btnnew;
    public JFXButton btndelete;
    public JFXButton btnupdate;
    final StudentBOImpl studentbo = BOFactory.getInstance().getBo(BOFactory.BoType.STUDENT);
    public ImageView lblsave;
    ObservableList<String>gender= FXCollections.observableArrayList("Male","Female");
    ObservableList<StudentDTO>studentlist= FXCollections.observableArrayList();
    public  void initialize(){

        DesableField();
        txtbirthday.setEditable(false);
        colno.setCellValueFactory(new PropertyValueFactory<>("no"));
        colid.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("student_name"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colcontact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colbirthday.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colgender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        cmbgender.setItems(gender);
        LoadAllStudents();
        tblstudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           if(newValue!=null){
               btnsave.setDisable(true);
               btndelete.setDisable(false);
               btnupdate.setDisable(false);
               txtid.setDisable(false);
               txtname.setDisable(false);
               txtaddress.setDisable(false);
               txtcontactno.setDisable(false);
               cmbgender.setDisable(false);
               txtbirthday.setDisable(false);
               txtid.setEditable(false);
               loadStudentdata(newValue);
           }
        });
        genStudentID();
        Search();
    }

    private void Search() {
        FilteredList<StudentDTO> filteredListdata=new FilteredList<>(studentlist, e->true);
        txtsearch.setOnKeyReleased(e->{
            txtsearch.textProperty().addListener(((observable, oldValue, newValue) ->{
                filteredListdata.setPredicate((Predicate<? super StudentDTO>) student->{
                    if(newValue==null||newValue.isEmpty()){
                        return true;
                    }
                    String lowercasefilter=newValue.toLowerCase();
                    if(String.valueOf(student.getNo()).contains(newValue)){
                        return true;
                    }else if(student.getStudent_id().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }else if(student.getStudent_name().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }else if(student.getAddress().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }
                    else if(student.getContact().toLowerCase().contains(lowercasefilter)){
                        return true;
                    }
                    else if(student.getGender().toLowerCase().contains(lowercasefilter)){
                        return true;
                    }
                    else if(student.getDob().toLowerCase().contains(lowercasefilter)){
                        return true;
                    }
                    return false;
                });
            } ));
            SortedList<StudentDTO> sortedList=new SortedList<>(filteredListdata);
            sortedList.comparatorProperty().bind(tblstudent.comparatorProperty());
            tblstudent.setItems(sortedList);
        });

    }

    private void genStudentID() {
        try {
            final String sid = studentbo.genstudentID();
            txtid.setText(sid);
            txtid.setEditable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadStudentdata(StudentDTO newValue) {
        txtid.setText(newValue.getStudent_id());
        txtname.setText(newValue.getStudent_name());
        txtaddress.setText(newValue.getAddress());
        txtcontactno.setText(newValue.getContact());
        cmbgender.setValue(newValue.getGender());
        txtbirthday.setValue(LocalDate.parse(newValue.getDob()));
        btnnext.setDisable(false);
    }

    private void DesableField() {
        txtid.setDisable(true);
        txtname.setDisable(true);
        txtaddress.setDisable(true);
        txtcontactno.setDisable(true);
        cmbgender.setDisable(true);
        txtbirthday.setDisable(true);
        btnupdate.setDisable(true);
        btndelete.setDisable(true);
        btnsave.setDisable(true);
        btnnext.setDisable(true);
    }

    private void LoadAllStudents() {
        studentlist.clear();
        try {
            final List<StudentDTO> findall = studentbo.findall();
            for (StudentDTO dto:findall) {
               studentlist.add(new StudentDTO(dto.getNo(),dto.getStudent_id(),dto.getStudent_name(),dto.getAddress(),
                       dto.getContact(),dto.getDob(),dto.getGender()));
            }
            tblstudent.setItems(studentlist);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnNextOnAction(ActionEvent actionEvent) throws IOException {
        ButtonType yes=new ButtonType("Yes");
        ButtonType no=new ButtonType("No");
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure Register ("+txtid.getText()+") " +
                "this Student?", yes, no);
        final Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.orElse(no)==yes){
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/RegistrationForm.fxml"));
            Parent load1 = loader.load();
            RegistrationFormController registrationFormController=loader.getController();
            registrationFormController.lblstudentid.setText(txtid.getText());
            root.getChildren().clear();
            root.getChildren().add(load1);
        }

    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if(CheckValidation()){
            ButtonType yes=new ButtonType("yes");
            ButtonType no=new ButtonType("No");
            final Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure Add this Student?", yes, no);
            final Optional<ButtonType> buttonType = alert.showAndWait();
            if(buttonType.orElse(no)==yes){
                try {
                    final boolean add = studentbo.add(new StudentDTO(0, txtid.getText(), txtname.getText(), txtaddress.getText(),
                            txtcontactno.getText(), txtbirthday.getValue().toString(), cmbgender.getValue().toString()));
                    if(add){
                        NotificationMessage.getNotification("/assert/icon/Successfully.png",txtid.getText()+" Student has" +
                                " Added " +
                                "Successfully!","Successfully!");
                        LoadAllStudents();
                        btnNewOnAction(actionEvent);
                        genStudentID();
                        DesableField();
                    }else {
                        NotificationMessage.getNotification("/assert/icon/Warning.png",txtid.getText()+" Student has Update " +
                                "Student Fail Added!","Warning!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private boolean CheckValidation() {
        if(Pattern.compile("^[S]{1}[0]{1,}[0-9]{1,}$").matcher(txtid.getText()).matches()){
            if(Pattern.compile("^[A-Z]{1,}[.]{1,}[A-z ]{1,}|[A-Z]{1}[A-z ]{1,}$").matcher(txtname.getText()).matches()){
                if(Pattern.compile("^[#.0-9a-zA-Z\\s,-]+$").matcher(txtaddress.getText()).matches()){
                    if(Pattern.compile("^[0-9]{10}$").matcher(txtcontactno.getText()).matches()){
                        if(cmbgender.getSelectionModel().getSelectedIndex()!=-1){
                            if(txtbirthday.getValue()!=null){
                                txtbirthday.setStyle("-fx-border-color: rgba(198,197,197,0.19)");
                                return true;
                            }else {
                                txtbirthday.setStyle("-fx-border-color: red");
                            }
                        }else {
                            cmbgender.setFocusColor(Paint.valueOf("red"));
                            cmbgender.requestFocus();
                        }
                    }else {
                        txtcontactno.setFocusColor(Paint.valueOf("red"));
                        txtcontactno.requestFocus();
                    }
                }else {
                    txtaddress.setFocusColor(Paint.valueOf("red"));
                    txtaddress.requestFocus();
                }
            }else {
                txtname.setFocusColor(Paint.valueOf("red"));
                txtname.requestFocus();
            }
        }else {
            txtid.setFocusColor(Paint.valueOf("red"));
            txtid.requestFocus();
        }
        return false;
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
        txtid.setDisable(false);
        txtname.setDisable(false);
        txtaddress.setDisable(false);
        txtcontactno.setDisable(false);
        cmbgender.setDisable(false);
        txtbirthday.setDisable(false);
        btnsave.setDisable(false);
        btnupdate.setDisable(true);
        btndelete.setDisable(true);
        txtname.clear();
        txtaddress.clear();
        txtcontactno.clear();
        txtbirthday.setValue(null);
        cmbgender.setValue(null);
        genStudentID();


    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        ButtonType yes=new ButtonType("yes");
        ButtonType no=new ButtonType("No");
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure Delete this Student?", yes, no);
        final Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.orElse(no)==yes){
            try {
                final boolean delete = studentbo.delete(txtid.getText());
                if(delete){
                    NotificationMessage.getNotification("/assert/icon/Successfully.png",txtid.getText()+" Student has " +
                            "Delete" +
                            " " +
                            "Successfully!","Successfully!");
                    LoadAllStudents();
                    btnNewOnAction(actionEvent);
                    genStudentID();
                    DesableField();
                }else {
                    NotificationMessage.getNotification("/assert/icon/Warning.png",txtid.getText()+" Student has " +
                            "Delete Fail!","Warning!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        ButtonType yes=new ButtonType("yes");
        ButtonType no=new ButtonType("No");
        final Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure Update this Student?", yes, no);
        final Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.orElse(no)==yes){
            if(CheckValidation()){
                try {
                    final boolean update = studentbo.update(new StudentDTO(0, txtid.getText(), txtname.getText(), txtaddress.getText(),
                            txtcontactno.getText(), txtbirthday.getValue().toString(), cmbgender.getValue().toString()));
                    if(update){
                        NotificationMessage.getNotification("/assert/icon/Successfully.png",txtid.getText()+" Student has Update Successfully!","Successfully!");
                        LoadAllStudents();
                        btnNewOnAction(actionEvent);
                        genStudentID();
                        DesableField();
                    }else {
                        NotificationMessage.getNotification("/assert/icon/Warning.png",txtid.getText()+" Student has " +
                                "Update Fail!","Warning!");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void MouseEnterdOnAction(MouseEvent mouseEvent) throws IOException {

        if(mouseEvent.getSource() instanceof ImageView){
            ImageView imageView=(ImageView) mouseEvent.getSource();
            switch(imageView.getId()){
                case "lbllogo":{
                }break;
            }
            ScaleTransition scaleT =new ScaleTransition(Duration.millis(200), imageView);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            imageView.setEffect(glow);
        }
    }

    public void MouseExitOnAction(MouseEvent mouseEvent) {

        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();
            icon.setEffect(null);
        }
    }


    public void MouseClickOnAction(MouseEvent mouseEvent) throws IOException {
        Parent root=null;
        root = FXMLLoader.load(this.getClass().getResource("/view/DashBoardForm.fxml"));
        Scene scene=new Scene(root);
        Stage primaryStage = (Stage) this.root.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        TranslateTransition tt = new TranslateTransition(Duration.millis(500), scene.getRoot());
        tt.setFromX(-scene.getWidth());
        tt.setToX(0);
        tt.play();
    }

    public void txtidSearchOnAction(ActionEvent actionEvent) {
        try {
            final StudentDTO studentDTO = studentbo.find(txtid.getText());
            loadStudentdata(studentDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
