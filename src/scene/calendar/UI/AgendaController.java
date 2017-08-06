package scene.calendar.UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import scene.calendar.CalendarController;
import scene.event.UI.EventViewController;
import scene.event.entity.Event;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

/**
 * Created by Liu Woon Kit on 13/6/2017.
 */
public class AgendaController implements Initializable {

    @FXML
    Label dayOfWeekLbl;

    @FXML
    Label dateLbl;

    @FXML
    AnchorPane eventsPane;

    @FXML
    GridPane agendaGrid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i = 0; i < 24; i++) {
            AnchorPane anchorPane = new AnchorPane();
            agendaGrid.add(anchorPane, 0, i);
            anchorPane.setPrefSize(Integer.MAX_VALUE, Integer.MAX_VALUE);

            if(i == 0)
                anchorPane.setStyle("-fx-border-color: white");
            else
                anchorPane.setStyle("-fx-border-color: transparent white white white");
        }
    }

    public void getTimes(GregorianCalendar date) {
        clearAgenda();
        dayOfWeekLbl.setText((new SimpleDateFormat("EEEE")).format(date.getTime()));
        dateLbl.setText((new SimpleDateFormat("d MMM YYYY")).format(date.getTime()));

        ArrayList<Event> events = CalendarController.getEventsOfDate(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmm");
        for(Event e : events) {
            insertTime(Double.valueOf(simpleDateFormat.format(e.getStartTime())), Double.valueOf(simpleDateFormat.format(e.getEndTime())), e);
        }
    }

    public void insertTime(double startTime, double endTime, Event event) {
        if(startTime < 0)
            startTime = 1;
        if(endTime > 2459)
            endTime = 2459;

        // Get last 2 digits and convert from 60 minutes to 50 minutes
        double startTimeMinutes = (startTime % 100) / 1.2;
        double endTimeMinutes = (endTime % 100) / 1.2;

        startTime = (startTime - (startTime % 100)) / 2.0;
        startTime += startTimeMinutes;

        endTime = (endTime - (endTime % 100)) / 2.0;
        endTime += endTimeMinutes;

        //System.out.println();

        //double x = eventsPane.getLayoutX();

        Button btn = new Button(event.getTitle());
        btn.setStyle("-fx-background-color: #9fa8da;");
        btn.setLayoutX(10);
        //System.out.println("Layout Y: " + startTime);
        btn.setLayoutY(startTime);
        btn.setMinSize((eventsPane.getWidth()/2),endTime - startTime);
        //System.out.println("Height: " + (endTime - startTime));

        eventsPane.getChildren().add(btn);

        btn.setOnAction(e -> {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../event/UI/EventView.fxml"));
            fxmlLoader.setController(new EventViewController(event));
            try {
                stage.setScene(new Scene(fxmlLoader.load()));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            stage.show();

        });
    }

    public void clearAgenda() {
        eventsPane.getChildren().clear();
    }
}