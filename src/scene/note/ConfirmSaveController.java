package scene.note;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ConfirmSaveController extends NotePageController implements Initializable {

    @FXML
    private Button doNotSaveButt;

    @FXML
    private Button saveButt;

    @FXML
    private Button cancelButt;

    private String control;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void doNotSave(ActionEvent event) throws IOException {
        control = "no save";
        closeStage(doNotSaveButt);
    }

    @FXML
    void save(ActionEvent event) throws IOException {
        control = "save";
        closeStage(saveButt);
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        closeStage(cancelButt);

    }

    public void setControl(String control) {
        this.control = control;
    }

    private void closeStage(Button btn){
        Stage currentWindow = (Stage)btn.getScene().getWindow();
        currentWindow.close();
    }

    public String getControl() {
        return control;
    }
}
