package scene.note.entity;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by eyoyu on 19/7/2017.
 */
public class test extends Application {
    private final String DEFAULT_SCREEN = "login/Login.fxml";
    private final String DEFAULT_ICON = "../resources/images/icon.png";
    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("../note.fxml"));
        primaryStage.setTitle("Personal Manager");
        primaryStage.setScene(new Scene(root));
        //primaryStage.getIcons().add(new Image(DEFAULT_ICON));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

