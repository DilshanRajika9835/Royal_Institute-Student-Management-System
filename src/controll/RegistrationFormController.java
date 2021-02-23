package controll;/*@author:Dilshan Rajika Withanachchi*/

import business.BOFactory;
import business.custom.impl.CourseBOImpl;
import business.custom.impl.RegistrationBoImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.CourseDTO;
import dto.RegistrationDTO;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.tm.NotificationMessage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class RegistrationFormController {
    public AnchorPane root;
    public ImageView lbllogo;
    public Label lblregno;
    public Label lbldate;
    public Label lblstudentid;
    public TableView <RegistrationDTO>tblregistration;
    public TableColumn colno;
    public TableColumn colregno;
    public TableColumn colstudentid;
    public TableColumn colcoursecode;
    public TableColumn colregdate;
    public TableColumn colregfee;
    public JFXButton btnregister;
    public JFXComboBox<String> cmbcourseid;
    public JFXTextField txtsearch;
    public JFXButton btnupdate;
    final CourseBOImpl courseBO = BOFactory.getInstance().getBo(BOFactory.BoType.COURSE);
    final RegistrationBoImpl registrationBo = BOFactory.getInstance().getBo(BOFactory.BoType.REGISTRATION);
    public JFXTextField txtfee;
    public JFXButton btndelete;
    ObservableList<String>courses= FXCollections.observableArrayList();
    ObservableList<RegistrationDTO>reglist= FXCollections.observableArrayList();
    public  void  initialize(){
        colno.setCellValueFactory(new PropertyValueFactory<>("no"));
        colregno.setCellValueFactory(new PropertyValueFactory<>("reg_no"));
        colstudentid.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colcoursecode.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        colregdate.setCellValueFactory(new PropertyValueFactory<>("reg_date"));
        colregfee.setCellValueFactory(new PropertyValueFactory<>("reg_fee"));
       loadcourses();
        cmbcourseid.setId("A");
        lbldate.setText(String.valueOf(LocalDate.now()));
        loadRegistration();
        tblregistration.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
               loadregData(newValue);
                cmbcourseid.setId("A");
            }
        });
    }
    private void loadregData(RegistrationDTO newValue) {
        btnregister.setDisable(true);
        btndelete.setDisable(false);
        btnupdate.setDisable(false);
        lblstudentid.setText(newValue.getStudent_id());
        cmbcourseid.setId("B");
        cmbcourseid.setValue(newValue.getCourse_id());
        lblregno.setText(newValue.getReg_no());
        lbldate.setText(newValue.getReg_date());
        txtfee.setText(String.valueOf(newValue.getReg_fee()));


    }

    private void loadRegistration() {
        reglist.clear();
        try {
            final List<RegistrationDTO> findall = registrationBo.findall();
            for (RegistrationDTO dto:findall) {
                reglist.add(new RegistrationDTO(dto.getNo(),dto.getReg_no(),dto.getStudent_id(),dto.getCourse_id(),
                        dto.getReg_date(),dto.getReg_fee()));

            }
            tblregistration.setItems(reglist);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadcourses() {
        try {
            final List<CourseDTO> getcourses = courseBO.findall();
            for (CourseDTO course :getcourses) {
                courses.add(course.getCourse_id());
            }
            cmbcourseid.setItems(courses);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        try{
            if(cmbcourseid.getValue().length()>0&&lblregno.getText().length()>0&&txtfee.getText().length()>0){
                ButtonType yes=new ButtonType("yes");
                ButtonType no=new ButtonType("No");
                final Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure Registered this Student?", yes, no);
                final Optional<ButtonType> buttonType = alert.showAndWait();
                if(buttonType.orElse(no)==yes){
                    try {
                        final boolean add = registrationBo.add(new RegistrationDTO(0, lblregno.getText(), lblstudentid.getText(), cmbcourseid.getValue().toString(),
                                lbldate.getText(),
                                Double.parseDouble(txtfee.getText())));
                        if(add){
                            NotificationMessage.getNotification("/assert/icon/Successfully.png",lblstudentid.getText()+" " +
                                    "Student " +
                                    "is" +
                                    " Registered " +
                                    "Successfully!","Successfully!");
                            loadRegistration();
                            clearField();
                            btnregister.setDisable(true);
                        }else {
                            NotificationMessage.getNotification("/assert/icon/Warning.png",lblstudentid.getText()+" Student " +
                                    "is " +
                                    "Registered Fail!","Warning!");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }else {
                NotificationMessage.getNotification("/assert/icon/Warning.png","Fill All Fields....","Warning!");

            }
        }catch (Exception e){
            NotificationMessage.getNotification("/assert/icon/Warning.png","Fill All Fields....","Warning!");
        }



    }

    private  void clearField(){
        lblregno.setText(null);
        txtfee.clear();
        lblstudentid.setText(null);
        cmbcourseid.setValue(null);
        lblregno.setText(null);

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

    public void MouseEnteredOnAction(MouseEvent mouseEvent) {
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

    public void txtfeeEnterOnAction(ActionEvent actionEvent) {
        btnRegisterOnAction(actionEvent);
    }

    public void cmbCourseOnAction(ActionEvent actionEvent) {
        if(cmbcourseid.getSelectionModel().getSelectedIndex()!=-1 && cmbcourseid.getId().equals("A")&&lblstudentid.getText().length()>0){
            lblregno.setText(null);
            String CourseID=cmbcourseid.getValue();
            String StudentID=lblstudentid.getText();
            try {
                final String SID = registrationBo.findRegistration(StudentID, CourseID);

                if(lblstudentid.getText().equals(SID)){
                    btnregister.setDisable(true);
                    btndelete.setDisable(false);
                    btnupdate.setDisable(false);
                    NotificationMessage.getNotification("/assert/icon/Warning.png",lblstudentid.getText()+
                                    " Student is Already Registered..."+"\n"+" Sorry Unable Progress to Registration !",
                            "Warning!");

                }else {
                    lblregno.setText(null);
                    final String regid= registrationBo.genRegID(CourseID);
                    lblregno.setText(regid);
                    btnregister.setDisable(false);
                    btndelete.setDisable(true);
                    btnupdate.setDisable(true);
                }
                cmbcourseid.setId("A");

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
            try {
                final boolean update = registrationBo.update(new RegistrationDTO(0, lblregno.getText(), lblstudentid.getText(),
                        cmbcourseid.getValue().toString(),
                        lbldate.getText(),
                        Double.parseDouble(txtfee.getText())));
                if(update){
                    NotificationMessage.getNotification("/assert/icon/Successfully.png",lblregno.getText()+" Student has" +
                            " Update " +
                            "Successfully!","Successfully!");
                    loadRegistration();
                    clearField();
                }else {
                    NotificationMessage.getNotification("/assert/icon/Warning.png",lblregno.getText()+" Student has Update" +
                            " " +
                            "Student Fail Added!","Warning!");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            if (lblstudentid.getText().length() > 0 && cmbcourseid.getValue().length() > 0 && lblregno.getText().length() > 0) {
                ButtonType yes = new ButtonType("yes");
                ButtonType no = new ButtonType("No");
                final Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure Cancel this Student Registration?",
                        yes, no);
                final Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.orElse(no) == yes) {
                    try {
                        final boolean delete = registrationBo.deleteRegistration(lblstudentid.getText(),
                                cmbcourseid.getValue(), lblregno.getText());
                        if (delete) {
                            NotificationMessage.getNotification("/assert/icon/Successfully.png", lblregno.getText() + " Student " +
                                    "Registration" +
                                    " Cancel " +
                                    "Successfully!", "Successfully!");
                            loadRegistration();
                            clearField();
                        } else {
                            NotificationMessage.getNotification("/assert/icon/Warning.png", lblregno.getText() + " Student " +
                                    "Registration Cancel  Fail!", "Warning!");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                NotificationMessage.getNotification("/assert/icon/Warning.png", " Select Student First!!", "Warning!");
            }
        }catch (Exception exception){
            NotificationMessage.getNotification("/assert/icon/Warning.png"," Select Student First!!","Warning!");

        }

    }

    public void lblBackMouseClickOnAction(MouseEvent mouseEvent) throws IOException {
        Parent root=null;
        root = FXMLLoader.load(this.getClass().getResource("/view/StudentForm.fxml"));
        Scene scene=new Scene(root);
        Stage primaryStage = (Stage) this.root.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        TranslateTransition tt = new TranslateTransition(Duration.millis(500), scene.getRoot());
        tt.setFromX(-scene.getWidth());
        tt.setToX(0);
        tt.play();
    }
}
