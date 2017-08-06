package scene.Task.UI;

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
import java.text.SimpleDateFormat;
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
    @FXML
    private Label date;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
    public Task getTask(){
        return task;
    }
    public void setTaskInfo(Task task,boolean setDate){

        // set date is left side
        this.task=task;
        String taskName=task.getTask_name();
        addLabel(taskName);
        if(task.getSdate()!=null){
            if(setDate){
                Calendar cal=TaskControllerKt.getCalendarByDate(task.getSdate());
                SimpleDateFormat sf=new SimpleDateFormat("EE");
                String dayOfWeek= sf.format(cal.getTime());
                int month=cal.get(Calendar.MONTH)+1;
                int day=cal.get(Calendar.DAY_OF_MONTH);

                date.setText(dayOfWeek+"\n"+day+"/"+month);

            }

            String timeLocation= task.getSdate();
            String[] data= timeLocation.split("-");
            timeLocation=data[2]+"/"+data[1]+"/"+data[0];


            if(task.getLocation()!=null&&task.getLocation()!=""){

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
