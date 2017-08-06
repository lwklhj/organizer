package scene.Task.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import resources.database.entity.User;
import resources.database.entity.UserController;
import scene.Task.TaskControllerKt;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hhf on 8/5/17.
 */
public class UserDetailsController implements Initializable {
    private User user;

    @FXML
    private Label adMinNoField;

    @FXML
    private Label nameField;

    @FXML
    private Label birthDateField;

    @FXML
    private Label genderField;

    @FXML
    private Label emailField;

    @FXML
    private Label phoneField;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setUserInfomation(String id) {
        user=UserController.getUserInformation(id);
        nameField.setText(user.getName());
        adMinNoField.setText(user.getUserID());
        birthDateField.setText(TaskControllerKt.getDateFormatYear().format(user.getBirthDate().getTime()));
        genderField.setText(user.getFullGender());
        emailField.setText(user.getEmail());
        phoneField.setText(user.getHpNumber()+"");

    }
    @FXML
    void close(ActionEvent event) {
        Stage stage=(Stage)((Node)event.getTarget()).getScene().getWindow();
        stage.close();

    }
}
