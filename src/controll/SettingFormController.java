package controll;/*@author:Dilshan Rajika Withanachchi*/

import business.BOFactory;
import business.SuperBO;
import business.custom.impl.LoginBOImpl;
import business.custom.impl.UserBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dto.LoginDTO;
import dto.UserDTO;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.tm.NotificationMessage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SettingFormController {
    public Pane loginpane;
    public JFXTextField txtusername;
    public Circle circleprofile;
    public JFXPasswordField txtprviouspassword;
    public JFXPasswordField txtnewpassword;
    public Label lblwarning1;
    /*====================================*/
    public TableView<LoginDTO> tbllogin;
    public TableColumn colLogNo;
    public TableColumn colLogUserName;
    public TableColumn colLogPassword;
    public TableColumn colLogDate;
    public TableColumn colLogTime;
    public TableColumn colStatus;
    /*====================================*/
    public TableView<UserDTO> tbluser;
    public TableColumn coluserNo;
    public TableColumn colUserName;
    public TableColumn colPassword;
    public TableColumn colLocation;
    public Circle Userprofile;
    public Label lblUserName;
    final LoginBOImpl loginBO = BOFactory.getInstance().getBo(BOFactory.BoType.LOGIN);
    final UserBOImpl userBO = BOFactory.getInstance().getBo(BOFactory.BoType.USER);
    public ImageView lblcamer;
    public JFXButton btnconform;
    public JFXButton btncheck;
    public Label lblmessage;
    public AnchorPane root;
    ObservableList<LoginDTO>login_List= FXCollections.observableArrayList();
    ObservableList<UserDTO>user_List= FXCollections.observableArrayList();
    String replacepath="src\\assert\\logo.png";
    public  void initialize(){

        colLogNo.setCellValueFactory(new PropertyValueFactory<>("no"));
        colLogUserName.setCellValueFactory(new PropertyValueFactory<>("user_name"));
        colLogPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colLogDate.setCellValueFactory(new PropertyValueFactory<>("login_date"));
        colLogTime.setCellValueFactory(new PropertyValueFactory<>("login_time"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("login_status"));

        coluserNo.setCellValueFactory(new PropertyValueFactory<>("no"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("profile"));
           loadLoginDetails();
             loadUserDetails();
             tbluser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                 if(newValue!=null){
                     setProfileImg(newValue);

                 }
             });
            lblwarning1.setVisible(false);
            lblmessage.setVisible(false);
            loginpane.setVisible(false);
            tbllogin.setVisible(false);
            tbluser.setVisible(false);
            Userprofile.setVisible(false);
            lblUserName.setVisible(false);
            lblcamer.setDisable(true);
        setDetails("","", "src\\assert\\user.png");

        }

    private void setProfileImg(UserDTO newValue) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(newValue.getProfile()));
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            Userprofile.setFill(new ImagePattern(image));
            lblUserName.setText(newValue.getUsername());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void loadUserDetails() {
        try {
            user_List.clear();
            final List<UserDTO> findall = userBO.findall();
            for (UserDTO dto:findall) {
              user_List.add(new UserDTO(dto.getNo(),dto.getUsername(),"*****",dto.getProfile()));
            }
            tbluser.setItems(user_List);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadLoginDetails() {
        login_List.clear();
        try {
            final List<LoginDTO> findall = loginBO.findall();
            for (LoginDTO login:findall) {
           login_List.add(new LoginDTO(login.getNo(),login.getUser_name(),"******",login.getLogin_date(),
                   login.getLogin_time(),login.getLogin_status()));
            }
            tbllogin.setItems(login_List);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ChangePasswordOnAction(ActionEvent actionEvent) {
        tbluser.setVisible(false);
        lblcamer.setDisable(true);
        btnconform.setDisable(true);
        txtnewpassword.setDisable(true);
        tbllogin.setVisible(false);
        Userprofile.setVisible(false);
        lblUserName.setVisible(false);
        loginpane.setVisible(true);

    }

    public void lblChangeprofile(MouseEvent mouseEvent) {

        try {
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Image");
            File filePath = fileChooser.showOpenDialog(stage);
            String path=filePath.getAbsolutePath();
            replacepath=path.replace("\\","\\\\");
            BufferedImage bufferedImage = ImageIO.read(filePath);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            circleprofile.setFill(new ImagePattern(image));
        } catch (Exception e) {
            NotificationMessage.getNotification("/assert/icon/Warning.png"," Profile Picture Not set" +
                    "try again..!","Fail!");
        }
    }

    public void btnConformOnAction(ActionEvent actionEvent) {
        try {
            final boolean add = userBO.addUser(new UserDTO(0, txtusername.getText(), txtnewpassword.getText(),
                    replacepath));
          if(add){
              final boolean delete = userBO.delete(txtusername.getText(), txtprviouspassword.getText());
              if(delete){
                  lblmessage.setVisible(true);
                  loadLoginDetails();
                  loadUserDetails();
                  txtnewpassword.clear();
                  txtprviouspassword.clear();
                  txtusername.clear();
                  setDetails("","", "src\\assert\\user.png");

              }
          }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CloseOnAction(ActionEvent actionEvent) throws IOException {
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

    public void AboutOnAction(ActionEvent actionEvent) {
    }

    public void UserOnAction(ActionEvent actionEvent) {
        loadUserDetails();
        tbluser.setVisible(true);
        tbllogin.setVisible(false);
        Userprofile.setVisible(true);
        lblUserName.setVisible(true);
        loginpane.setVisible(false);
    }

    public void LoginOnAction(ActionEvent actionEvent) {
        loadLoginDetails();
        tbluser.setVisible(false);
        tbllogin.setVisible(true);
        Userprofile.setVisible(false);
        lblUserName.setVisible(false);
        loginpane.setVisible(false);
    }

    public void btnCheckOnAction(ActionEvent actionEvent) {

        try {
            final List<UserDTO> userlist = userBO.findUser(txtusername.getText(), txtprviouspassword.getText());
            if(userlist.size()>0){
                lblwarning1.setVisible(false);
                for (int i = 0; i < userlist.size(); i++) {
                    if(userlist.get(i).getUsername().equals(txtusername.getText())&&userlist.get(i).getPassword().equals(txtprviouspassword.getText())){
                        setDetails(userlist.get(i).getUsername(),userlist.get(i).getPassword(),userlist.get(i).getProfile());
                        replacepath=userlist.get(i).getProfile();
                        txtnewpassword.setDisable(false);
                        btnconform.setDisable(false);
                        lblcamer.setDisable(false);
                    }
                }
            }else {
                lblwarning1.setVisible(true);
                setDetails(txtusername.getText(),txtprviouspassword.getText(), "src\\assert\\user.png");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setDetails(String username, String password, String profile) {

        try {
            BufferedImage bufferedImage = ImageIO.read(new File(profile));
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            circleprofile.setFill(new ImagePattern(image));
            txtusername.setText(username);
            txtprviouspassword.setText(password);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void txtkeyReleasedOnAction(KeyEvent keyEvent) {
        lblmessage.setVisible(false);
        txtnewpassword.setDisable(true);
        btnconform.setDisable(true);
        lblcamer.setDisable(true);
    }
}
