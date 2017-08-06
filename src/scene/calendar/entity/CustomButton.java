package scene.calendar.entity;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import resources.database.UserAccess;
import scene.Task.TaskControllerKt;
import scene.Task.entity.Task;
import scene.calendar.CalendarController;
import scene.event.EventController;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by Liu Woon Kit on 31/5/2017.
 */
public class CustomButton extends Button {
    private GregorianCalendar date;
    private AnchorPane content;

    private Label dateLabel = new Label();
    private Label eventLabel = new Label();
    private Label taskLabel = new Label();

    public CustomButton() {
        content = new AnchorPane();
        content.getChildren().add(dateLabel);
        content.getChildren().add(eventLabel);
        content.getChildren().add(taskLabel);

        eventLabel.setLayoutY(40);
        taskLabel.setLayoutY(55);

        super.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        super.setGraphic(content);
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public void display() {
        Platform.runLater(() -> {
            super.setOnAction(event -> CalendarController.showAgenda(date));
            dateLabel.setText(Integer.toString(date.get(GregorianCalendar.DAY_OF_MONTH)));

            int eventNum = EventController.getNumOfEventsOfDate(CalendarController.dateFormat(date));
            if(eventNum > 0) {
                eventLabel.setText(eventNum + " event");
            }

            Platform.runLater(() -> {
                ArrayList<Task> taskArrayList = TaskControllerKt.getTaskByUser(UserAccess.getUser().getUserID(), TaskControllerKt.getYearDateFormat().format(date.getTime()));
                int taskNum = 0;

                for(Task task : taskArrayList) {
                    taskNum++;
                }

                if(taskNum > 0){
                    taskLabel.setText(taskNum + " task");
                }
            });
        });
    }

    public void suicide() {
        dateLabel.setText(null);
        eventLabel.setText(null);
        taskLabel.setText(null);
        super.setOnAction(null);
    }
}