package scene.Task;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import resources.database.DB;

import javax.sql.rowset.CachedRowSet;
import java.util.Calendar;

/**
 * Created by hhf on 7/4/17.
 */
public class test extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //TaskControllerKt.addTask("help","01/01/2016 12:30","01/01/2017 13:40","description",1,0,"location","event","133u");
        /*System.out.println("dddd");

        DB.updateUserEvent("INSERT INTO task(id,name,start,end,taskDesc,priority,repeatType,location,userID) VALUES(1,'help','2016/1/1 12:30:00','2017/1/1 13:40:00','description',1,0,'location','133u')");

        CachedRowSet sets=DB.read("select * from task");
        while(sets.next()){
            System.out.print(sets.getString("start")+sets.getString("id")+sets.getString("end"));
        }*/
        Parent loader=FXMLLoader.load(getClass().getResource("../Task/UI/TaskMain.fxml"));
        Scene scene=new Scene(loader);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
