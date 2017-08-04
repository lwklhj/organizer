package scene.Task.UI;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import scene.Task.TaskControllerKt;
import scene.Task.entity.Task;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

/**
 * Created by hhf on 7/4/17.
 */
public class TaskBarController implements Initializable{
    @FXML
    private VBox taskInfoVbox;

    private Task task;

    private TaskMainController tc;





    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }
    public Task getTask(){
        return task;
    }
    public void setTaskInfo(Task task){
        this.task=task;
        String taskName=task.getTask_name();
        addLabel(taskName);
        if(task.getStart()!=null){

            String timeLocation= TaskControllerKt.calStringTime(task.getStart());
            if(task.getLocation()!=null||task.getLocation()!=""){
                timeLocation+=" at "+task.getLocation();
            }
            addLabel(timeLocation);
        }
        if(task.getEvent()!=null||task.getEvent()!=""){
            String event=task.getEvent();
            addLabel(event);
        }
        taskInfoVbox.setStyle("-fx-background-color:"+task.getPriority().getColor());




    }
    @FXML
    void showTaskDetails(MouseEvent event) {

    }
    private void addLabel(String content){
        Label label=new Label(content);
        label.setPadding(new Insets(1,1,1,3));
        label.setFont(new Font("Consola",15));
        label.setTextFill(Color.WHITE);
        taskInfoVbox.getChildren().add(label);


    }
}
