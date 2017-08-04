package resources.util.ConfirmationBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConfirmationBox implements Initializable {
    private boolean result;
    private Stage stage;

    @FXML
    Label contentTextLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public ConfirmationBox() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ConfirmationBox.fxml"));
        fxmlLoader.setController(this);
        stage = new Stage();
        stage.setTitle("Confirmation");

        stage.initModality(Modality.APPLICATION_MODAL);
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }

    @FXML
    void confirm(ActionEvent event) {
        result = true;
        stage.close();
    }

    @FXML
    void cancel(ActionEvent event) {
        result = false;
        stage.close();
    }

    public void setTitle(String title) {
        stage.setTitle(title);
    }

    public void setContentText(String contentText) {
        contentTextLbl.setText(contentText);
    }

    public boolean getResult() {
        return result;
    }
}
