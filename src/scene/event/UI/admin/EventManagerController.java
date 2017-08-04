package scene.event.UI.admin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import scene.event.EventController;
import scene.event.UI.EventObjectController;
import scene.event.entity.Event;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EventManagerController implements Initializable {

    @FXML
    private HBox ownEvents;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateDisplay();
    }

    @FXML
    void createEvent() {
        Stage stage = new Stage();
        try {
            EventEditorController eventEditorController = new EventEditorController();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EventEditor.fxml"));
            fxmlLoader.setController(eventEditorController);
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.initModality(Modality.APPLICATION_MODAL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }

    private void populateDisplay() {
        ArrayList<Event> eventArrayList = EventController.getOrganizerEvents();
        for(Event event : eventArrayList) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../EventObject.fxml"));
            EventObjectController eventObjectController = new EventObjectController(event);

            fxmlLoader.setController(eventObjectController);
            try {
                ownEvents.getChildren().add(fxmlLoader.load());
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            eventObjectController.setEditorMode(true);
        }
    }
}