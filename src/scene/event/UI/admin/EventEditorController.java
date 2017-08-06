package scene.event.UI.admin;


import com.jfoenix.controls.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    private JFXTextArea eventDescField;

    @FXML
    private JFXDatePicker eventDateField;

    @FXML
    private JFXTimePicker startTimeField;

    @FXML
    private JFXTimePicker endTimeField;

    @FXML
    private JFXTextField eventLocationField;

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

    }

    public boolean validateFields() {
        boolean result = true;

        String errMsg = "";

        if(eventTitleField.getText().trim().isEmpty()) {
            errMsg += "\nEnter a title";
            result = false;
        }

        if(eventDescField.getText().trim().isEmpty()) {
            errMsg += "\nEnter a description";
            result = false;
        }

        if(eventDateField.getValue() == null) {
            errMsg += "\nEnter a date";
            result = false;
        }

        if(startTimeField.getValue() == null) {
            errMsg += "\nEnter a start time";
            result = false;
        }

        if(endTimeField.getValue() == null) {
            errMsg += "\nEnter an end time";
            result = false;
        }

        if(startTimeField.getValue() != null && endTimeField.getValue() != null) {
            if (startTimeField.getValue().isAfter(endTimeField.getValue())) {
                errMsg += "\nStart time cannot be after end time";
                //endTimeErrMsg.setText("End time cannot be before start time ");
                result = false;
            }
        }

        if(eventLocationField.getText().trim().isEmpty()) {
            errMsg += "\nEnter a location";
            result = false;
        }

        if(!result) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Check input fields");
            alert.setContentText(errMsg);
            alert.showAndWait();
        }

        return result;
    }

    @FXML
    void cancel(ActionEvent event) {
        ((Stage)((Node)event.getTarget()).getScene().getWindow()).close();
    }

    @FXML
    void saveEvent() {
        if(validateFields() == false) {
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

        ((Stage)eventTitleField.getScene().getWindow()).close();
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