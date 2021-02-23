import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Appinitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

primaryStage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("view/Loginform.fxml"))));
      /*  primaryStage.initStyle(StageStyle.UNDECORATED);*/
        primaryStage.setTitle("Royal Institute");
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.getIcons().add(new Image("assert/logo.png"));
        primaryStage.show();



    }
}
