package scene.profile.UI;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import resources.database.UserAccess;
import resources.database.entity.User;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Liu Woon Kit on 28/6/2017.
 */
public class ProfileController implements Initializable {
    @FXML
    private Label username;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXPasswordField passwordConfirmationField;

    @FXML
    private JFXTextField hpField;

    @FXML
    private JFXTextField emailField;

    private User user = UserAccess.getUser();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(user.getName());
        //emailField.setText(user.getEmail());
    }

    @FXML
    void update() {
        if(new String(passwordField.getText()) != new String(passwordConfirmationField.getText())) {
            //display error
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Update profile");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() != ButtonType.OK) {
            return;
        }

        //UserDAO.updateUser(new String(passwordField.getText(), hpField.getText(), emailField.getText()));
    }


}
