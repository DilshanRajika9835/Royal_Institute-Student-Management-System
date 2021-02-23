package controll;/*@author:Dilshan Rajika Withanachchi*/

import business.BOFactory;
import business.SuperBO;
import business.custom.impl.CourseBOImpl;
import business.custom.impl.RegistrationBoImpl;
import business.custom.impl.StudentBOImpl;
import dto.CourseDTO;
import dto.RegistrationDTO;
import dto.StudentDTO;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.util.List;

public class DetailsFormController {
    public Label lblstudent;
    public Label lblcourse;
    public Label lblregistration;
    public AnchorPane root;
    final StudentBOImpl studentBO = BOFactory.getInstance().getBo(BOFactory.BoType.STUDENT);
    final CourseBOImpl courseBO = BOFactory.getInstance().getBo(BOFactory.BoType.COURSE);
    final RegistrationBoImpl registrationBo = BOFactory.getInstance().getBo(BOFactory.BoType.REGISTRATION);
        public void initialize(){
          loadDetails();
        }

    private void loadDetails() {
        try {
            final List<StudentDTO> student = studentBO.findall();
            lblstudent.setText(String.valueOf(student.size()));
            final List<CourseDTO> course = courseBO.findall();
            lblcourse.setText(String.valueOf(course.size()));
            final List<RegistrationDTO> register = registrationBo.findall();
            lblregistration.setText(String.valueOf(register.size()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
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

    public void MoterMachanicOnAction(ActionEvent actionEvent) {
        initUi("Motor Mechanics","This is Student is Registered Motor MechanicsCourse","CT0301");
    }

    public  void initUi(String title,String description,String CID) {
        System.out.println("Cid Is  "+CID);
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/view/CourseWiseStudent.fxml"));

        try {
            Parent load1 = loader.load();
            CourseWiseStudentController courseWiseStudentController=loader.getController();
            courseWiseStudentController.lbltitle.setText(title);
            courseWiseStudentController.lbldescription.setText(description);
            courseWiseStudentController.loadRegisterStudent(CID);
            root.getChildren().clear();
            root.getChildren().add(load1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void QuantitySurveyingOnAction(ActionEvent actionEvent) throws IOException {
       initUi("Quantity Surveying","This is Student is Registered Quantity Surveying Course","CT0402");

    }

    public void ElectronicOnAction(ActionEvent actionEvent) {
        initUi("Electronics","This is Student is Registered Electronics Course","CT0330");
    }

    public void ForeignOnAction(ActionEvent actionEvent) {
        initUi("Foreign languages - English","This is Student is Registered Foreign languages - EnglishCourse",
                "CT0545");
    }

    public void ComputerHardWareOnAction(ActionEvent actionEvent) {
        initUi("Computer Hardware","This is Student is Registered Computer Hardware Course","CT0412");
    }

    public void AboutOnAction(ActionEvent actionEvent) {

    }

    public void LoginOnAction(ActionEvent actionEvent) {
    }

    public void MouseEnterOnAction(MouseEvent mouseEvent) {
    }

    public void MouseExitOnAction(MouseEvent mouseEvent) {

    }
}
