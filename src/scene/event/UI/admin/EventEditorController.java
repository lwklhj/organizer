package scene.event.UI.admin;


import com.jfoenix.controls.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import scene.event.EventController;
import scene.event.entity.Event;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Liu Woon Kit on 11/7/2017.
 */
public class EventEditorController implements Initializable {
    /*@FXML
    WebView webView;*/

    private Event event;

    private boolean editMode = false;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private JFXTextField eventTitleField;

    @FXML
    private TextArea eventDescField;

    @FXML
    private JFXDatePicker eventDateField;

    @FXML
    private JFXTimePicker startTimeField;

    @FXML
    private JFXTimePicker endTimeField;

    @FXML
    private JFXTextField eventLocationField;

    // Error message

    @FXML
    private Label eventTitleErrMsg;

    @FXML
    private Label eventDescErrMsg;

    @FXML
    private Label eventDateErrMsg;

    @FXML
    private Label startTimeErrMsg;

    @FXML
    private Label endTimeErrMsg;

    @FXML
    private Label eventLocationErrMsg;

    // Event creation
    public EventEditorController() {

    }

    // Event modify
    public EventEditorController(Event event){
        this.event = event;
        editMode = true;
        // Set fields once fxml is fully loaded
        Platform.runLater(() -> {
            eventTitleField.setText(event.getTitle());
            eventDescField.setText(event.getDesc());
            eventDateField.setValue(LocalDate.of(event.getDate().get(GregorianCalendar.YEAR), event.getDate().get(GregorianCalendar.MONTH), event.getDate().get(GregorianCalendar.DAY_OF_MONTH)));
            startTimeField.setValue(LocalTime.parse(event.getStartTime().toString()));
            endTimeField.setValue(LocalTime.parse(event.getEndTime().toString()));
            eventLocationField.setText(event.getLocation());
            deleteBtn.setVisible(true);
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*startTimeField.valueProperty().addListener(event -> {
            if(endTimeField.getValue() != null) {
                if (startTimeField.getValue().isAfter(endTimeField.getValue())) {
                    System.out.println("ERROR: Start Time cannot be after End Time.");
                }
            }
        });

        endTimeField.valueProperty().addListener(event -> {
            if(startTimeField.getValue() != null) {
                if(startTimeField.getValue().isAfter(endTimeField.getValue())) {
                    System.out.println("ERROR: Start Time cannot be after End Time.");
                }
            }

        });*/
    }

    public boolean validateFields() {
        boolean result = true;

        eventTitleErrMsg.setText(null);
        eventDescErrMsg.setText(null);
        eventDateErrMsg.setText(null);
        startTimeErrMsg.setText(null);
        endTimeErrMsg.setText(null);
        eventLocationErrMsg.setText(null);

        if(eventTitleField.getText().trim().isEmpty()) {
            eventTitleErrMsg.setText("Enter a title");
            result = false;
        }

        if(eventDescField.getText().trim().isEmpty()) {
            eventDescErrMsg.setText("Enter a description");
            result = false;
        }

        if(startTimeField.getValue() == null) {
            startTimeErrMsg.setText("Enter a start time");
            result = false;
        }

        if(endTimeField.getValue() == null) {
            endTimeErrMsg.setText("Enter a end time");
            result = false;
        }

        if(startTimeField.getValue().isAfter(endTimeField.getValue())) {
            startTimeErrMsg.setText("Start time cannot be before end time");
            endTimeErrMsg.setText("WAT");
            result = false;
        }

        if(eventLocationField.getText().trim().isEmpty()) {
            eventLocationErrMsg.setText("Enter a location");
            result = false;
        }

        return result;
    }

    @FXML
    void cancel(ActionEvent event) {
        ((Stage)((Node)event.getTarget()).getScene().getWindow()).close();
    }

    @FXML
    void saveEvent() {
        if(!validateFields()) {
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Save event?");
        //alert.setContentText("");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.CANCEL) {
            return;
        }

        if(editMode) {
            event.setTitle(eventTitleField.getText());
            event.setDesc(eventDescField.getText());
            event.setLocation(eventLocationField.getText());
            event.setDate(Date.valueOf(eventDateField.getValue()));
            event.setStartTime(Time.valueOf(startTimeField.getValue()));
            event.setEndTime(Time.valueOf(endTimeField.getValue()));
            EventController.updateOrganizerEvent(event);
        }
        else {
            EventController.createOrganizerEvent(eventTitleField.getText(), eventDescField.getText(), eventLocationField.getText(), Date.valueOf(eventDateField.getValue()), Time.valueOf(startTimeField.getValue()), Time.valueOf(endTimeField.getValue()));
        }

        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setHeaderText("Event saved");
        alert2.showAndWait();
    }

    @FXML
    void deleteEvent(ActionEvent e) {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setTitle("Confirmation Dialog");
        alert1.setHeaderText("Delete event?");
        //alert.setContentText("");

        Optional<ButtonType> result = alert1.showAndWait();


        if(result.get() == ButtonType.CANCEL) {
            return;
        }

        EventController.deleteOrganizerEvent(event);
        ((Stage)deleteBtn.getScene().getWindow()).close();

        Alert alert2 = new Alert(Alert.AlertType.WARNING);
        alert2.setHeaderText("Event has been deleted");
        alert2.showAndWait();
    }
}