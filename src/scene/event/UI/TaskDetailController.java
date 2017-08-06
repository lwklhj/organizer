package scene.event.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import scene.Task.entity.Task;

import java.net.URL;
import java.util.ResourceBundle;

public class TaskDetailController implements Initializable {
    @FXML
    private Label endDate;

    @FXML
    private Label repeat;

    @FXML
    private Label description;

    @FXML
    private Label taskName;

    @FXML
    private Label location;

    @FXML
    private Label priority;

    @FXML
    private Label startDate;

    @FXML
    void close(ActionEvent event) {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void setTask(Task t) {
        taskName.setText(t.getTask_name());
        if(t.getSdate()!=null){
            startDate.setText( t.getSdate());
        }
        else{
            startDate.setText("----------");
        }
        if(t.getEdate()!=null){
            endDate.setText( t.getEdate());
        }
        else{
            endDate.setText("----------");
        }

        if(t.getDescription()!=null&&!t.getDescription().equals( "" )){
            description.setText(t.getDescription()  );
        }
        else{
            description.setText("----------");
        }
        if(t.getLocation()!=null){
            location.setText(t.getLocation() );
        }
        else{
            location.setText("----------");
        }
        priority.setText(t.getPriority().name());
        repeat.setText(t.getRepeat().name() );


    }
}