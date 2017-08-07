package scene.note;

import resources.database.DB;
import resources.database.UserAccess;
import scene.note.entity.Note;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class NotePageController implements Initializable{
    private String userID = UserAccess.getUser().getUserID();
    private ArrayList<Note> noteArr = new ArrayList<Note>();

    @FXML
    private Label noteTitle;

    @FXML
    private ImageView pinImage;

    @FXML
    private Button backAndSaveButt;

    private Note note;

    private boolean isPined;

    private Image unpinnedImage;

    private Image pinnedImage;

    @FXML
    public TextArea noteContent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isPined = false;
    }
    @FXML
    void backAndSave(ActionEvent event) throws IOException {
        String control = null;
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("ConfirmSave.fxml"));
        Parent root=loader.load();
        ConfirmSaveController controller = loader.getController();

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
        control=controller.getControl();
        if(control == null){


            }else if(control.equals("no save")){

            closeStage(backAndSaveButt);

        }else if(control.equals("save")){

            note.setPined(isPined);
            String sqlQuery = "UPDATE note SET content= '"+noteContent.getText()+"'  WHERE noteID="+note.getNoteID();

            DB.update(sqlQuery);
            int i=0;
            if(isPined) i=1;
            DB.update("UPDATE note SET isPined="+i+" WHERE noteID="+note.getNoteID());
            closeStage(backAndSaveButt);
        }
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
        noteTitle.setText(note.getTitle());
        noteContent.setText(note.getContent());
        isPined=note.isPined();
        displayPin();
    }

    public Label getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(Label noteTitle) {
        this.noteTitle = noteTitle;
    }

    public TextArea getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(TextArea noteContent) {
        this.noteContent = noteContent;
    }

    private void closeStage(Button btn){

        Stage currentWindow = (Stage)btn.getScene().getWindow();
        currentWindow.close();
    }

    @FXML
    void pinPressed(ActionEvent event) {
        isPined=!isPined;
        displayPin();
    }

    public void displayPin(){
        if(isPined){
            pinImage.setImage(pinnedImage);
        }else{
            pinImage.setImage(unpinnedImage);
        }
    }
}
