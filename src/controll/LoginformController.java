package controll;
/*@author:Dilshan Rajika Withanachchi*/

import business.BOFactory;
import business.custom.impl.LoginBOImpl;
import business.custom.impl.UserBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dto.LoginDTO;
import dto.UserDTO;
import javafx.animation.FadeTransition;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.tm.NotificationMessage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class LoginformController {
    public AnchorPane root;
    public JFXPasswordField txthidepassword;
    public JFXTextField txtusername;
    public ImageView lblcloseeye;
    public ImageView lblopeneye;
    public JFXTextField txtshowpassword;
    public Circle circleprofile;
    public Label lblsingup;
    public Label lbllogin;
    public ImageView lblloguser;
    public ImageView lbllogpassword;
    public JFXButton btnlogin;
    public JFXButton btnsingup;
    public Label lbldontaccount;
    public Label lbltitle;
    public Label lblsubtitle;


    public Label lblsingupsuccss;
    public Label lblsingupwarning;
    public ImageView lblsingupcam;
    public Circle singupprofile;
    public JFXTextField txtsingupusername;
    public JFXTextField txtsingupshowpwd;
    public JFXPasswordField txtsinguphidepwd;
    public ImageView lblsingupcloseeye;
    public ImageView lblsingupopeneye;
    public Hyperlink lblclick2;
    public Label lblclick1;
    public JFXButton btnreg;
    public ImageView lblsingupuser;
    public ImageView lblsinguppwd;
    public Label lbldetails;
    public Label lbllogwarning;
    public ImageView lblback;

    /*---------------------------------------------------*/
    String replacepath="src\\\\assert\\\\logo.png";
    String  status="Login Fail!";
    final LoginBOImpl loginBO= BOFactory.getInstance().getBo(BOFactory.BoType.LOGIN);
    final UserBOImpl userBO= BOFactory.getInstance().getBo(BOFactory.BoType.USER);
    private String password_one;
    private String password_two;

    public  void initialize(){
        txtshowpassword.setVisible(false);
        lblopeneye.setVisible(false);
        lbllogwarning.setVisible(false);
        lblback.setVisible(false);
        setProfile();
        lblSingUpPage(false);
    }

    private void lblSingUpPage(boolean option) {
        lblsingupsuccss.setVisible(false);
        lblsingupwarning.setVisible(false);
        lblsingupcam.setVisible(option);
        singupprofile.setDisable(option);
        txtsingupshowpwd.setVisible(false);
        txtsinguphidepwd.setVisible(option);
        txtsingupusername.setVisible(option);
        lblsingupcloseeye.setVisible(option);
        lblsingupopeneye.setVisible(false);
        singupprofile.setVisible(option);
        lblclick1.setVisible(false);
        lblclick2.setVisible(false);
        btnreg.setVisible(option);
        lblsingup.setVisible(option);
        lblsingupuser.setVisible(option);
        lblsinguppwd.setVisible(option);
        lbldetails.setVisible(option);

    }
    public void lblChangSingUpProfileClickOnAction(MouseEvent mouseEvent) {
        try {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Image");
            File filePath = fileChooser.showOpenDialog(stage);
            String path = filePath.getAbsolutePath();
            replacepath = path.replace("\\", "\\\\");
            setProfile();
            try {
                BufferedImage bufferedImage = ImageIO.read(filePath);
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                singupprofile.setFill(new ImagePattern(image));
            } catch (Exception e) {
                NotificationMessage.getNotification("/assert/icon/Warning.png", " Profile Picture Not set" +
                        "try again..!", "Fail!");
            }
        }catch (Exception e){
            NotificationMessage.getNotification("/assert/icon/Warning.png", " Profile Picture Not set " +
                    "try again..!", "Fail!");
        }
    }

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        try {
            final boolean registered= userBO.addUser(new UserDTO(0,txtsingupusername.getText().trim(), password_two.trim(),
                    replacepath));
            if(registered){
                lblsingupsuccss.setVisible(true);
                lblclick1.setVisible(true);
                lblclick2.setVisible(true);
                lbldetails.setVisible(false);
                txtusername.setText(txtsingupusername.getText());
                txtsingupusername.clear();
                txtsinguphidepwd.clear();
                txtsingupshowpwd.clear();
            }else {
                lblsingupwarning.setVisible(true);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void txtSingUpHideKeyRealsedOnAction(KeyEvent keyEvent) {
        lbllogwarning.setVisible(false);
        lblback.setVisible(false);
        password_two=txtsinguphidepwd.getText();

    }

    public void txtSingUpShowKeyRealsedOnAction(KeyEvent keyEvent) {
        lbllogwarning.setVisible(false);
        lblback.setVisible(false);
        password_two=txtsingupshowpwd.getText();

    }

    public void lblSingUpShowPasswordOnAction(MouseEvent mouseEvent) {
        txtsingupshowpwd.setVisible(false);
        lblsingupopeneye.setVisible(false);
        txtsinguphidepwd.setVisible(true);
        lblsingupcloseeye.setVisible(true);
        txtsingupshowpwd.requestFocus();
        txtsinguphidepwd.setText(password_two);
    }

    public void lblSingUpHidePasswordOnAction(MouseEvent mouseEvent) {
        txtsingupshowpwd.setVisible(true);
        lblsingupopeneye.setVisible(true);
        txtsinguphidepwd.setVisible(false);
        lblsingupcloseeye.setVisible(false);
        txtsingupshowpwd.requestFocus();
        txtsingupshowpwd.setText(password_two);
    }

    public void txtClickHereOnAction(ActionEvent actionEvent) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), lbllogin);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
        lblSingUpPage(false);
        lblLoginPage(true);
        lblback.setVisible(false);
    }

    public void lblMouseClickBacklOnAction(MouseEvent mouseEvent) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), lbllogin);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
        lblSingUpPage(false);
        lblLoginPage(true);
        lblback.setVisible(false);
    }


    private void lblLoginPage(boolean option) {
        lbllogin.setVisible(option);
        circleprofile.setVisible(option);
        lbltitle.setVisible(option);
        lblsubtitle.setVisible(option);
        lblloguser.setVisible(option);
        lbllogpassword.setVisible(option);
        lblopeneye.setVisible(option);
        lblcloseeye.setVisible(option);
        btnlogin.setVisible(option);
        lbldontaccount.setVisible(option);
        btnsingup.setVisible(option);
        txtshowpassword.setVisible(false);
        txthidepassword.setVisible(option);
        txtusername.setVisible(option);

    }
    private void setProfile() {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(replacepath));
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            circleprofile.setFill(new ImagePattern(image));
            singupprofile.setFill(new ImagePattern(image));
        } catch (Exception e) {
            NotificationMessage.getNotification("/assert/icon/Warning.png"," Profile Picture Not set" +
                    "try again..!","Fail!");
        }
    }
    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        if(txtusername.getText().trim().length()>0){
            if(txtshowpassword.getText().trim().length()>0|txthidepassword.getText().length()>0){
                if(checkLogin(txtusername.getText().trim(),password_one.trim())){
                    status="Login Successfully";
                    addLogindetails();
                    Stage stage=new Stage();
                    stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/DashBoardForm.fxml"))));
                    stage.setTitle("Royal Institute");
                    stage.centerOnScreen();
                    stage.resizableProperty().setValue(false);
                    stage.getIcons().add(new Image("assert/logo.png"));
                    stage.show();
                    Stage stage1=(Stage)txtusername.getScene().getWindow();
                    stage1.close();
                }else {
                    lbllogwarning.setVisible(true);
                    status="Login Fail!";
                    addLogindetails();
                }
            }else {
                txtshowpassword.setFocusColor(Paint.valueOf("red"));
                txthidepassword.setFocusColor(Paint.valueOf("red"));
                txtshowpassword.requestFocus();
                txthidepassword.requestFocus();
            }
        }else {
            txtusername.setFocusColor(Paint.valueOf("red"));
            txtusername.requestFocus();
        }

    }
    private void addLogindetails() {
        try {
            loginBO.add(new LoginDTO(0,txtusername.getText(),password_one,LocalDate.now().toString(),getTime(),
                    status));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean checkLogin(String username,String password){
        try {
            final String code = loginBO.checkLogin(username, password);
            if(password.equals(code)){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public void lblMouseClickHidePasswordOnAction(MouseEvent mouseEvent) {
        txtshowpassword.setVisible(false);
        lblopeneye.setVisible(false);
        txthidepassword.setVisible(true);
        lblcloseeye.setVisible(true);
        txthidepassword.requestFocus();
        txthidepassword.setText(password_one);

    }

    public void lblMouseClickShowPasswordOnAction(MouseEvent mouseEvent) {
        txtshowpassword.setVisible(true);
        lblopeneye.setVisible(true);
        txthidepassword.setVisible(false);
        lblcloseeye.setVisible(false);
        txtshowpassword.requestFocus();
        txtshowpassword.setText(password_one);
    }

    public void txtShowKeyRealsedOnAction(KeyEvent keyEvent) {
        password_one=txtshowpassword.getText();
    }

    public void txtHideKeyRealsedOnAction(KeyEvent keyEvent) {
        password_one=txthidepassword.getText();
    }
    public void btnSingUpOnAction(ActionEvent actionEvent) {
        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), lblsingup);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
        lblSingUpPage(true);
        lblLoginPage(false);
        lbllogwarning.setVisible(false);
        lblback.setVisible(true);

    }
    private String getTime(){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss a");
        return sdf.format(date);
    }

}
