package scene.event.UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import scene.event.UI.admin.EventEditorController;
import scene.event.entity.Event;

import java.io.IOException;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import static javafx.stage.Modality.APPLICATION_MODAL;

/**
 * Created by Liu Woon Kit on 29/6/2017.
 */
public class EventObjectController implements Initializable {
    private Event event;
    private final String DAY_OF_WEEK[] = {"SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"};
    private final String MONTH[] = new String[]{"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "November", "December"};

    @FXML
    private AnchorPane paneClick;

    @FXML
    private Label monthLbl;

    @FXML
    private Label dayOfMonthLbl;

    @FXML
    private Label dayOfWeekLbl;

    @FXML
    private Label eventTitleLbl;

    @FXML
    private Label eventDescLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Event date
        monthLbl.setText(MONTH[event.getDate().get(GregorianCalendar.MONTH)]);
        dayOfMonthLbl.setText(event.getDate().get(GregorianCalendar.DAY_OF_MONTH) + "");
        dayOfWeekLbl.setText(DAY_OF_WEEK[event.getDate().get(GregorianCalendar.DAY_OF_WEEK) - 1]);

        // Event Title
        eventTitleLbl.setText(event.getTitle());
        if(event.getDesc().length() > 15) {
            eventDescLbl.setText(event.getDesc().substring(0, 15) + "...");
        }
        else {
            eventDescLbl.setText(event.getDesc());
        }

        // on event click
        paneClick.setOnMouseClicked(e -> {
            Stage stage = new Stage();
            stage.initModality(APPLICATION_MODAL);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EventView.fxml"));
            fxmlLoader.setController(new EventViewController(event));
            try {stage.setScene(new Scene(fxmlLoader.load()));} catch (IOException e1) {
                e1.printStackTrace();
            }

            // Work around for setResizable bug
            stage.setResizable(false);
            stage.sizeToScene();

            stage.show();
        });
    }

    public void setEditorMode(boolean b) {
        if(b) {
            paneClick.setOnMouseClicked(e -> {
                Stage stage = new Stage();
                stage.initModality(APPLICATION_MODAL);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin/EventEditor.fxml"));
                fxmlLoader.setController(new EventEditorController(event));
                try {stage.setScene(new Scene(fxmlLoader.load()));} catch (IOException e1) {
                    e1.printStackTrace();
                }

                // Work around for setResizable bug
                stage.setResizable(false);
                stage.sizeToScene();

                stage.show();
            });
        }
    }

    public EventObjectController(Event event) {
        this.event = event;
    }
}