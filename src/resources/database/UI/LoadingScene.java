package resources.database.UI;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingScene implements Initializable {
    Stage stage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public LoadingScene() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        //stage.initStyle(StageStyle.TRANSPARENT);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoadingScene.fxml"));
        fxmlLoader.setController(this);
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }

    public void close() {
        try {
            wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stage.close();
    }


}
