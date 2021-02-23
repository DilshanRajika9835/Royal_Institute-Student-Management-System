package controll;/*@author:Dilshan Rajika Withanachchi*/

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

public class DashboardFormController {
    public Label lbltitle;
    public Label lbldescription;
    public ImageView lblsetting;
    public ImageView lbldetails;
    public ImageView lblcourse;
    public ImageView lblstudent;
    public ImageView lbllogo;
    public AnchorPane root;

    public void lblMouseEnterOnAction(MouseEvent mouseEvent) {
        if(mouseEvent.getSource() instanceof ImageView){
            ImageView imageView=(ImageView) mouseEvent.getSource();
            switch(imageView.getId()){

                case "lblstudent":{
                    lbltitle.setText("Manage Student");
                    lbldescription.setText("Click to add, edit, delete, search or view Student");

                }break;

                case "lblcourse":{
                    lbltitle.setText("Manage Course");
                    lbldescription.setText("Click to add, edit, delete, search or view Course");

                }break;

                case "lbldetails":{
                    lbltitle.setText("View Details");
                    lbldescription.setText("Click here if you want to want student courses registration!");

                }break;

                case "lblsetting":{
                    lbltitle.setText("Setting");
                    lbldescription.setText("Click if you want to change your details password..");

                }break;
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

    public void lblMouseExitOnAction(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();
            icon.setEffect(null);
            lbltitle.setText("Welcome to  Royal Institute");
            lbldescription.setText(" As the largest, private sector, higher education network in Sri Lanka with 40 centres island-wide, ROYAL creates ...");
        }
    }


    public void MouseClickOnAction(MouseEvent mouseEvent) throws IOException {
        ImageView imageView=(ImageView) mouseEvent.getSource();
        Parent root=null;
        switch (imageView.getId()){
            case "lblstudent":{
                root = FXMLLoader.load(this.getClass().getResource("/view/StudentForm.fxml"));

            }break;
            case "lblcourse":{
                root = FXMLLoader.load(this.getClass().getResource("/view/CourseForm.fxml"));

            }break;
            case "lbldetails":{
                root = FXMLLoader.load(this.getClass().getResource("/view/DetailsForm.fxml"));

            }break;
            case "lbllogo":{
                root = FXMLLoader.load(this.getClass().getResource("/view/Loginform.fxml"));

            }break;
            case "lblsetting":{
                root = FXMLLoader.load(this.getClass().getResource("/view/SettingForm.fxml"));

            }break;

        }
        if(root!=null){
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
    }

