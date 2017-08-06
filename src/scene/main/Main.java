package scene.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {root = FXMLLoader.load(getClass().getResource("../login/UI/LoginScene.fxml"));} catch (IOException e) {}
        //try {root = FXMLLoader.load(getClass().getResource("../event/UI/admin/EventEditor.fxml"));} catch (IOException e) {}

        Scene scene = new Scene(root);

        // Set scene title
        primaryStage.setTitle("CCA Organizer");

        // Set scene icon
        primaryStage.getIcons().addAll(
                new Image(getClass().getResourceAsStream("../../resources/images/icon_64x64.png")),
                new Image(getClass().getResourceAsStream("../../resources/images/icon_32x32.png")),
                new Image(getClass().getResourceAsStream("../../resources/images/icon_16x16.png"))
        );

        // Load custom fonts
        /*Font.loadFont(getClass().getResourceAsStream("../../resources/fonts/Questrial-Regular.ttf"), 12);
        //Font.loadFont(getClass().getResourceAsStream("../../resources/fonts/Ubuntu-Regular.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("../../resources/fonts/Raleway-Light.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("../../resources/fonts/Raleway-Thin.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("../../resources/fonts/Roboto-Thin.ttf"), 12);
        Font.loadFont(getClass().getResourceAsStream("../../resources/fonts/Roboto-Medium.ttf"), 12);*/

        // Work around for setResizable bug
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}