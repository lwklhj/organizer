package scene.Task.UI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import scene.Task.Priority;
import scene.Task.TaskControllerKt;
import scene.event.entity.Event;


import javax.naming.spi.InitialContextFactory;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

/**
 * Created by hhf on 6/29/17.
 */
public class AddTaskController implements Initializable{
    @FXML
    private JFXTextField taskNameTextField;

    @FXML
    private JFXTextField startDateTimeTextField;

    @FXML
    private JFXTextField endDateTimeTextField;

    @FXML
    private JFXTextField priorityTextField;

    @FXML
    private JFXTextField repeatTextField;

    @FXML
    private JFXTextField peopleTextField;

    @FXML
    private JFXTextField locationTextField;

    @FXML
    private JFXTextArea descriptionTextArea;

    @FXML
    private HBox startButton;

    @FXML
    private HBox endButton;

    @FXML
    private HBox priorityButton;

    @FXML
    private HBox repeatButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        endButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("dateTimePicker.fxml"));
                try {
                    AnchorPane ap=loader.load();
                    PriorityPickerController rpc=loader.getController();
                    //rpc.
                    Stage stage=new Stage(StageStyle.UNDECORATED);
                    Point2D pos=floatUIPos(event,endDateTimeTextField);
                    stage.setX(pos.getX());
                    stage.setY(pos.getY());

                    stage.setScene(new Scene(ap));
                    stage.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        @Override
                        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                            if(newValue==false) stage.close();
                        }
                    });
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("dateTimePicker.fxml"));
                try {
                    AnchorPane ap=loader.load();
                    PriorityPickerController rpc=loader.getController();
                    //rpc.
                    Stage stage=new Stage(StageStyle.UNDECORATED);
                    Point2D pos=floatUIPos(event,startDateTimeTextField);
                    stage.setX(pos.getX());
                    stage.setY(pos.getY());

                    stage.setScene(new Scene(ap));
                    stage.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        @Override
                        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                            if(newValue==false) stage.close();
                        }
                    });
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        priorityButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                FXMLLoader loader=new FXMLLoader(getClass().getResource("PriorityPicker.fxml"));
                try {
                    AnchorPane ap=loader.load();
                    PriorityPickerController rpc=loader.getController();
                    //rpc.
                    Stage stage=new Stage(StageStyle.UNDECORATED);
                    Point2D pos=floatUIPos(event,priorityTextField);
                    stage.setX(pos.getX());
                    stage.setY(pos.getY());
                    stage.setScene(new Scene(ap));
                    stage.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        @Override
                        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                            if(newValue==false) stage.close();
                        }
                    });
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        repeatButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("RepeatPicker.fxml"));
                try {
                    AnchorPane ap=loader.load();
                    RepeatPickerController rpc=loader.getController();
                    //rpc.
                    Stage stage=new Stage(StageStyle.UNDECORATED);
                    stage.setScene(new Scene(ap));
                    Point2D pos=floatUIPos(event,repeatTextField);
                    stage.setX(pos.getX());
                    stage.setY(pos.getY());
                    stage.focusedProperty().addListener(new ChangeListener<Boolean>() {
                        @Override
                        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                            if(newValue==false) stage.close();
                        }
                    });
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });




    }


    @FXML
    void cancel(ActionEvent event) {
        Stage stage=(Stage)((Node)event.getTarget()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void save(ActionEvent event) {
        String task=taskNameTextField.getText();
        String start=startDateTimeTextField.getText();
        String end=endDateTimeTextField.getText();
        int priority=Integer.parseInt(priorityTextField.getText());

        int repeat=Integer.parseInt(repeatTextField.getText());
        String peoples=peopleTextField.getText();
        String location=locationTextField.getText();
        String description=descriptionTextArea.getText();

        TaskControllerKt.addTask(task,start,end,description,priority,repeat,location,"help hello","1234567");
        cancel(event);
    }

    private Point2D floatUIPos(MouseEvent event, JFXTextField field){
        Window window=((Node)(event.getTarget())).getScene().getWindow();
        Point2D winPos=new Point2D(window.getX(),window.getY());
        Scene scene=((Node)(event.getTarget())).getScene();
        Point2D scenPos=new Point2D(scene.getX(),scene.getY());
        Point2D nodPos=field.localToScene(0,0);
        return new Point2D(winPos.getX()+scenPos.getX()+nodPos.getX(),winPos.getY()+scenPos.getY()+nodPos.getY()+field.getHeight());


    }







}
