package controll;/*@author:Dilshan Rajika Withanachchi*/

import business.BOFactory;
import business.SuperBO;
import business.custom.impl.CourseBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.custom.impl.CourseDAOImpl;
import dto.CourseDTO;
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
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class CourseFormController {
    public AnchorPane root;
    public ImageView lbllogo;
    public JFXTextField txtcode;
    public JFXTextField txtname;
    public JFXTextField txtduration;
    public JFXButton btnnew;
    public TableView <CourseDTO>tblcourse;
    public TableColumn colno;
    public TableColumn colcode;
    public TableColumn colname;
    public TableColumn colduration;
    public JFXTextField txtsearch;
    public JFXTextField txtfee;
    public TableColumn colfee;
    public JFXButton btnsave;
    public JFXButton btndelete;
    public JFXButton btnupdate;
    final CourseBOImpl courseBO = BOFactory.getInstance().getBo(BOFactory.BoType.COURSE);
    ObservableList<CourseDTO>courselist= FXCollections.observableArrayList();
    public  void initialize(){
            LoadallCourse();
            Search();
        }

    private void LoadallCourse() {
        colno.setCellValueFactory(new PropertyValueFactory<>("no"));
        colcode.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("course_name"));
        colfee.setCellValueFactory(new PropertyValueFactory<>("course_fee"));
        colduration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        DesableField();
        loadAllCourses();
        tblcourse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
              loadDetails(newValue);
            }
        });
    }
    private void Search() {
        FilteredList<CourseDTO> filteredListdata=new FilteredList<>(courselist, e->true);
        txtsearch.setOnKeyReleased(e->{
            txtsearch.textProperty().addListener(((observable, oldValue, newValue) ->{
                filteredListdata.setPredicate((Predicate<? super CourseDTO>) course->{
                    if(newValue==null||newValue.isEmpty()){
                        return true;
                    }
                    String lowercasefilter=newValue.toLowerCase();
                    if(String.valueOf(course.getNo()).contains(newValue)){
                        return true;
                    }else if(course.getCourse_id().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }else if(course.getCourse_name().toLowerCase().contains(lowercasefilter)){

                        return true;
                    }else if(String.valueOf(course.getCourse_fee()).toLowerCase().contains(lowercasefilter)){

                        return true;
                    }
                    else if(course.getDuration().toLowerCase().contains(lowercasefilter)){
                        return true;
                    }
                    return false;
                });
            } ));
            SortedList<CourseDTO> sortedList=new SortedList<>(filteredListdata);
            sortedList.comparatorProperty().bind(tblcourse.comparatorProperty());
            tblcourse.setItems(sortedList);
        });

    }
    private void loadDetails(CourseDTO dto) {
       txtcode.setText(dto.getCourse_id());
       txtname.setText(dto.getCourse_name());
       txtfee.setText(Double.toString(dto.getCourse_fee()));
       txtduration.setText(dto.getDuration());
       btnsave.setDisable(true);
       btndelete.setDisable(false);
       btnupdate.setDisable(false);
       txtcode.setEditable(false);
        txtcode.setDisable(false);
        txtname.setDisable(false);
        txtfee.setDisable(false);
        txtduration.setDisable(false);
    }

    private void loadAllCourses() {
        courselist.clear();
        try {
            final List<CourseDTO> findall = courseBO.findall();
            for (CourseDTO dto:findall) {
               courselist.add(new CourseDTO(dto.getNo(),dto.getCourse_id(),dto.getCourse_name(),dto.getDuration(),
                       dto.getCourse_fee()));
            }
            tblcourse.setItems(courselist);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void DesableField() {
        txtcode.setDisable(true);
        txtname.setDisable(true);
        txtfee.setDisable(true);
        txtduration.setDisable(true);
        btnupdate.setDisable(true);
        btndelete.setDisable(true);
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
        txtcode.setDisable(false);
        txtname.setDisable(false);
        txtfee.setDisable(false);
        txtduration.setDisable(false);
        txtcode.clear();
        txtname.clear();
        txtfee.clear();
        txtduration.clear();
        btnsave.setDisable(false);
        btndelete.setDisable(true);
        btnupdate.setDisable(true);
        txtcode.setEditable(true);
    }

    public void txtDurationEnterOnAction(ActionEvent actionEvent) {
        btnSaveOnAction(actionEvent);
    }

    public void txtCourseSearchOnAction(ActionEvent actionEvent) {
        try {
            final CourseDTO courseDTO = courseBO.find(txtcode.getText());
            loadDetails(courseDTO);
        } catch (Exception e) {
            e.printStackTrace();
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

    public void MouseEnterOnAction(MouseEvent mouseEvent) {
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

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        try {
            if(CheckValidation()){
                ButtonType yes=new ButtonType("yes");
                ButtonType no=new ButtonType("No");
                final Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure Save this Course?", yes, no);
                final Optional<ButtonType> buttonType = alert.showAndWait();
                if(buttonType.orElse(no)==yes){
                    final boolean delete = courseBO.delete(txtcode.getText());
                    if(delete){
                        NotificationMessage.getNotification("/assert/icon/Successfully.png",txtcode.getText()+" " +
                                "Course has Delete Successfully!","Successfully!");
                        loadAllCourses();
                        btnNewOnAction(actionEvent);
                    }else {
                        NotificationMessage.getNotification("/assert/icon/Warning.png",txtcode.getText()+" " +
                                "Course has Delete Fail!","Warning!");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        if(CheckValidation()) {
            try {
                ButtonType yes = new ButtonType("yes");
                ButtonType no = new ButtonType("No");
                final Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure Save this Course?", yes, no);
                final Optional<ButtonType> buttonType = alert.showAndWait();
                if (buttonType.orElse(no) == yes) {
                    final boolean update = courseBO.update(new CourseDTO(0, txtcode.getText(), txtname.getText(), txtduration.getText(),
                            Double.parseDouble(txtfee.getText())));
                    if (update) {
                        NotificationMessage.getNotification("/assert/icon/Successfully.png", txtcode.getText() + " " +
                                "Course has Update Successfully!", "Successfully!");
                        loadAllCourses();
                        btnNewOnAction(actionEvent);
                    } else {
                        NotificationMessage.getNotification("/assert/icon/Warning.png", txtcode.getText() + " " +
                                "Course has Update Fail!", "Warning!");
                    }
                }
            }catch (Exception e){
               e.printStackTrace();
            }
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if(CheckValidation()){
            ButtonType yes=new ButtonType("yes");
            ButtonType no=new ButtonType("No");
            final Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure Save this Course?", yes, no);
            final Optional<ButtonType> buttonType = alert.showAndWait();
            if(buttonType.orElse(no)==yes){
                try {
                    final boolean add = courseBO.add(new CourseDTO(0, txtcode.getText(), txtname.getText(), txtduration.getText(),
                            Double.parseDouble(txtfee.getText())));
                    if(add){
                        NotificationMessage.getNotification("/assert/icon/Successfully.png",txtcode.getText()+" " +
                                "Course has Added Successfully!","Successfully!");
                        loadAllCourses();
                        btnNewOnAction(actionEvent);
                    }else {
                        NotificationMessage.getNotification("/assert/icon/Warning.png",txtcode.getText()+" " +
                                "Course has Added Fail!","Warning!");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean CheckValidation() {
        if(Pattern.compile("^[C]{1}[T]{1}[0-9]{1,}$").matcher(txtcode.getText()).matches()){
            if(Pattern.compile("^[[A-z ][-]]{3,}$").matcher(txtname.getText()).matches()){
                if(Pattern.compile("^[0-9]{1,}|[0-9.]{1,}[0-9]{1}$").matcher(txtfee.getText()).matches()){
                    if(Pattern.compile("^[0-9]{1,}[A-z]{1,}$").matcher(txtduration.getText()).matches()){
                                        return true;
                    }else {
                        txtduration.setFocusColor(Paint.valueOf("red"));
                        txtduration.requestFocus();
                    }
                }else {
                    txtfee.setFocusColor(Paint.valueOf("red"));
                    txtfee.requestFocus();
                }
            }else {
                txtname.setFocusColor(Paint.valueOf("red"));
                txtname.requestFocus();
            }
        }else {
            txtcode.setFocusColor(Paint.valueOf("red"));
            txtcode.requestFocus();
        }
        return false;
    }
}
