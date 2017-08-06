package scene.event.UI;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import resources.database.entity.User;
import resources.database.entity.UserController;
import scene.Task.TaskControllerKt;
import scene.Task.UI.UserDetailsController;
import scene.Task.entity.Task;
import scene.event.entity.Event;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewEventDetailController implements Initializable{
    @FXML
    private JFXListView<Task> taskView;

    @FXML
    private Label evntLabel;

    @FXML
    private JFXListView<User> memberView;

    private ObservableList<User> userList;
    private  ObservableList<Task> taskList;

    @FXML
    private Label eventNameField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        memberView.setOnMouseClicked( new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    ObservableList<User> list = memberView.getSelectionModel().getSelectedItems();

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Task/UI/UserDetails.fxml"));

                    try {
                        Parent userInfo = loader.load();
                        UserDetailsController controller = loader.getController();
                        controller.setUserInfomation(list.get(0).getUserID());

                        Stage stage = new Stage();
                        //stage.initOwner(((Node) (event.getTarget())).getScene().getWindow());
                        //stage.initModality(Modality.WINDOW_MODAL);

                        stage.setTitle("User Infomation");
                        stage.setScene(new Scene(userInfo));
                        stage.showAndWait();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    memberView.getSelectionModel().clearSelection();
                }catch (NullPointerException e){}
            }
        } );

        taskView.setOnMouseClicked( new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    ObservableList<Task> list = taskView.getSelectionModel().getSelectedItems();

                    //load
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("TaskDetail.fxml"));

                    try {
                        Parent userInfo = loader.load();
                        TaskDetailController  controller = loader.getController();
                        controller.setTask(list.get(0));

                        Stage stage = new Stage();
                        //stage.initOwner(((Node) (event.getTarget())).getScene().getWindow());
                        //stage.initModality(Modality.WINDOW_MODAL);

                        stage.setTitle("");
                        stage.setScene(new Scene(userInfo));
                        stage.showAndWait();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    taskView.getSelectionModel().clearSelection();
                }catch (NullPointerException e){}
            }
        } );
    }

    public void setData(Event e){
        ArrayList<User> users= UserController.getEventMember( e.getId() );
        ArrayList<Task> arr=TaskControllerKt.getTaskByEvent( e.getId() );

        userList= FXCollections.observableArrayList( users );
        taskList=FXCollections.observableArrayList(arr);

        memberView.getItems().setAll( userList );
        taskView.getItems().setAll( taskList );
        eventNameField.setText(e.getTitle());
    }
}