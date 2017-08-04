package scene.login.UI;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scene.login.LoginController;
import scene.login.entity.User;

// awt for app to browser bridge
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginSceneController implements Initializable {
    private LoginController loginController = new LoginController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private JFXTextField userIDField;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    void forgotPassword(ActionEvent event) {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI("https://cfapps.nyp.edu.sg/CF/SIMS/ResetPin/pin_reset_form.cfm"));
        } catch (IOException | URISyntaxException e) {}
    }

    @FXML
    void loginClick(ActionEvent event) {
        if(!loginController.verifyUser(userIDField.getText(), new String(passwordField.getText()))) {
            System.out.println("Error: Wrong username or password");
            return;
        }

        // Continue to main scene
        Parent root= null;
        try {root = FXMLLoader.load(getClass().getResource("../../main/MainScene_2.fxml"));} catch (IOException e) {}

        Scene scene=new Scene(root);
        Stage stage = (Stage)((Node)event.getTarget()).getScene().getWindow();

        stage.hide();
        stage.setScene(scene);

        //stage.setUserData(user);

        // Work around for setResizable bug
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();

        stage.show();
    }
}