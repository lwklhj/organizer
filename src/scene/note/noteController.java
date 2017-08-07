package scene.note;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import resources.database.DB;
import resources.database.UserAccess;
import scene.note.entity.Note;



import javax.sql.rowset.CachedRowSet;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class noteController implements Initializable {


    private String userID = UserAccess.getUser().getUserID();
    public ObservableList<Note> othersArr = FXCollections.observableArrayList();

    public ObservableList<String> groupArr= FXCollections.observableArrayList();

    private String currentGroup;

    @FXML
    private ImageView pinImage;

    @FXML
    private AnchorPane others;

    @FXML
    private VBox groupList;
    //private int noteID=Note.getNoteID();

    @FXML
    private Button addGroup;

    @FXML
    public AnchorPane pinned;

    public ObservableList<Note> pinnedArr =  FXCollections.observableArrayList();

    //Note note = new Note();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        retrieveGroupFolder();

        if(groupArr.size()!=0) {
            //util.Util.prln(groupArr.size() + "");
            currentGroup = groupArr.get(0);
        }
        retrieveNote(currentGroup);
    }

    @FXML
    void addNewNote(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewNote.fxml"));
        Parent root=loader.load();
        NewNoteController controller=loader.<NewNoteController>getController();
        controller.setCurrentGroup(currentGroup);


        Stage stage = new Stage();

        stage.setScene(new Scene(root));

        stage.showAndWait();
        retrieveNote(currentGroup);
    }

    @FXML
    void addNewGroup(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewGroup.fxml"));
        Parent root=loader.load();
        //noteMainController ctr=loader.getController();
        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setScene(scene);

        stage.showAndWait();
        retrieveGroupFolder();
    }

    protected void retrieveGroupFolder(){

        DB db=new DB();
        CachedRowSet rs=db.read("SELECT * FROM groupFolder WHERE userID ='"+userID+"'  ");

        groupArr.clear();
        try {
            if(rs!=null) {
                while (rs.next()) {
                    groupArr.add(rs.getString("groupName"));
                }
                displayGroup();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void displayGroup(){
        groupList.getChildren().clear();
        for(int i=0; i<groupArr.size(); i++){
            Button button = new Button(groupArr.get(i));
            button.setPrefSize(145, 56);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    currentGroup=button.getText();
                    retrieveNote(currentGroup);
                }
            });
            groupList.getChildren().add(button);
        }
    }

    private void retrieveNote(String groupName){
        othersArr.clear();
        pinnedArr.clear();

        DB db=new DB();
        CachedRowSet rs=db.read("SELECT * FROM note WHERE groupName='"+groupName+"'");
        try {
            while(rs.next()){
                if(rs.getInt("isPined")>0){
                    pinnedArr.add(new Note(rs.getInt("noteID"),rs.getString("groupName"), rs.getString("title"), rs.getString("content"),true));

                }else{
                    othersArr.add(new Note(rs.getInt("noteID"),rs.getString("groupName"), rs.getString("title"), rs.getString("content"),false));
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        displayNote();

    }
    public void displayNote(){
        others.getChildren().clear();
        pinned.getChildren().clear();
        double width=120;
        double height=120;
        int row=0;
        int column=0;
        int maxPerRow=6;
        //int index=0;
        for(int i = 0; i< othersArr.size(); i++){

            others.getChildren().removeAll(); //clear all button
            Button button=new Button(othersArr.get(i).getTitle());
            button.setMinWidth(100);
            button.setMaxWidth(100);
            button.setMinHeight(100);
            button.setMaxHeight(200);


            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(event.getButton().equals( MouseButton.PRIMARY)) {
                        try {
                            openNote(others.getChildren().indexOf(button),othersArr);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }else if(event.getButton().equals(MouseButton.SECONDARY)) {
                        showContextMenu(button,others.getChildren().indexOf(button),othersArr);
                    }
                }
            });

            button.relocate(row*width,column*height);
            row++;
            if(row>=maxPerRow){
                row=0;
                column++;
            }

            others.getChildren().add(button);
        }
        row = 0;
        column = 0;
        for(int i=0; i<pinnedArr.size(); i++){
            pinned.getChildren().removeAll(); //clear all button
            Button button=new Button(pinnedArr.get(i).getTitle());
            button.setMinWidth(100);
            button.setMaxWidth(100);
            button.setMinHeight(100);
            button.setMaxHeight(200);

            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(event.getButton().equals(MouseButton.PRIMARY)) {
                        try {
                            openNote(pinned.getChildren().indexOf(button),pinnedArr);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }else if(event.getButton().equals(MouseButton.SECONDARY)) {
                        showContextMenu(button,pinned.getChildren().indexOf(button),pinnedArr);
                    }
                }
            });
            button.relocate(row*width,column*height);
            row++;
            if(row>=maxPerRow){
                row=0;
                column++;
            }
            pinned.getChildren().add(button);

        }
    }

    private void openNote(int i,ObservableList<Note> arr)throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NotePage.fxml"));
        Parent root=loader.load();
        NotePageController controller = loader.<NotePageController>getController();
        controller.setNote(arr.get(i));
        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setScene(scene);

        stage.showAndWait();
        retrieveNote(currentGroup);
    }

    private void showContextMenu(Button btn,int index,ObservableList<Note> arr){
        ContextMenu contextMenu=new ContextMenu();
        MenuItem delete=new MenuItem("delete");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Note note=arr.get( index );

                DB.update("DELETE FROM note WHERE noteID="+note.getNoteID());
                retrieveNote(currentGroup);
            }
        });
        contextMenu.getItems().addAll(delete);
        btn.setContextMenu(contextMenu);
    }
}
