package scene.note;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import resources.database.DB;
import resources.database.UserAccess;
import scene.note.entity.Note;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class NewGroupController implements Initializable {
    private String adminNo = UserAccess.getUser().getUserID();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private Button cancel;

    @FXML
    private Button confirm;

    @FXML
    private TextField enteredGroupName;
    //Note note = new Note();
    @FXML
    void confirm(ActionEvent event) {
        //System.out.println(String.format("INSERT INTO groupFolder(groupName) VALUES(\"%s\",\"%s\")",enteredGroupName.getText(), adminNo));

        DB.update(String.format("INSERT INTO groupFolder VALUES(\"%s\",\"%s\")",enteredGroupName.getText(), adminNo));
        //note.confirm();
        Stage currentWindow = (Stage) cancel.getScene().getWindow();
        currentWindow.close();
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage currentWindow = (Stage) cancel.getScene().getWindow();
        currentWindow.close();
    }
}

