package scene.event.UI;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import scene.event.EventController;
import scene.event.entity.Event;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Liu Woon Kit on 29/6/2017.
 */
public class EventViewController implements Initializable {

    private Event event;

    @FXML
    private Label eventTitle;

    @FXML
    private Label eventOrganizer;

    @FXML
    private Label eventDate;

    @FXML
    private Label eventLocation;

    @FXML
    private Label eventDesc;

    @FXML
    private JFXButton buttonAction;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayEventDetails();
    }

    public EventViewController(Event event) {
        this.event = event;
    }

    public void displayEventDetails() {
        eventTitle.setText(event.getTitle());
        eventOrganizer.setText("Organized by: " + EventController.getOrganizerName(event));
        eventDesc.setText(event.getDesc());
        eventDesc.setWrapText(true);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        dateFormat.setCalendar(event.getDate());
        eventDate.setText(dateFormat.format(event.getDate().getTime()) + " " + timeFormat.format(event.getStartTime()) + "-" + timeFormat.format(event.getEndTime()));

        eventLocation.setText(event.getLocation());
        setButtonLabel();
    }

    public void setButtonLabel() {
        if(event.isRegistered()) {
            buttonAction.setText("LEAVE");
        }
        else
            buttonAction.setText("JOIN");
    }

    @FXML
    void actionTrigger() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(buttonAction.getText().toLowerCase() + " event?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.CANCEL) {
            return;
        }

        event.setRegistered(!event.isRegistered());
        setButtonLabel();
        EventController.updateUserEvent(event);
    }
}