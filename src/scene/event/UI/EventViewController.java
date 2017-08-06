package scene.event.UI;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import scene.event.EventController;
import scene.event.entity.Event;

import java.io.IOException;
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
    private JFXButton joinOrLeaveEventBtn;

    @FXML
    private JFXButton viewEventTasksBtn;

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
        setButtons();
    }

    public void setButtons() {
        if(event.isRegistered()) {
            joinOrLeaveEventBtn.setText("LEAVE");
            viewEventTasksBtn.setVisible(true);
        }
        else {
            joinOrLeaveEventBtn.setText("JOIN");
            viewEventTasksBtn.setVisible(false);
        }
    }

    @FXML
    void joinOrLeaveEvent() {
        // show confirmation box
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(joinOrLeaveEventBtn.getText().toLowerCase() + " event?");
        Optional<ButtonType> result = alert.showAndWait();

        if(result.get() == ButtonType.CANCEL) {
            return;
        }

        // if yes
        event.setRegistered(!event.isRegistered());
        setButtons();
        EventController.updateUserEvent(event);
    }

    @FXML
    void viewEventTasks() {
        Stage stage=new Stage();
        FXMLLoader loader=new FXMLLoader(getClass().getResource( "ViewEventDetail.fxml" ));
        try {
            Parent root=loader.load();
            ViewEventDetailController ve=loader.getController();
            ve.setData( event );
            stage.setScene( new Scene( root ) );
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}