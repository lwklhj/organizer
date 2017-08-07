package scene.note.entity;

import resources.database.DB;
import resources.database.UserAccess;
import scene.note.NewGroupController;
import scene.note.NotePageController;
import scene.note.noteController;
import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import resources.database.UserAccess;

public class Note {
    private String adminNo = UserAccess.getUser().getUserID();
    private String group;
    private String title;
    private String content;
    private boolean isPined;
    private int noteID;
    //private static String userID = UserAccess.getUser().getUserID();

    //public static noteController note = new noteController();
   //public static NewGroupController groupname = new NewGroupController();
    //public static NotePageController notepage = new NotePageController();

    public Note(){};

    public Note(int id,String group, String title, String content, boolean isPined) {
        this.group = group;
        this.title = title;
        this.content = content;
        this.isPined=isPined;
        this.noteID = id;
    }

    public boolean isPined() {
        return isPined;
    }

    public void setPined(boolean pined) {
        isPined = pined;
    }

    public String getGroup() {return group;}

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void storeData() {
        System.out.println(String.format("insert into note(groupName,title,content,isPined,userID) value(\"%s\",\"%s\",\"%s\",%d,\"%s\")",group,title,content,1, adminNo));
        int val=0;
        if(isPined)
            val=1;
        DB.update(String.format("insert into note(groupName,title,content,isPined,userID) value(\"%s\",\"%s\",\"%s\",%d,\"%s\")",group,title,content,val, adminNo));
    }

    public  int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    /*public static void retrieveGroupFolder() {
        DB db = new DB();
        CachedRowSet rs = db.read( "SELECT * FROM groupFolder WHERE userID ='" + userID + "'  " );

        noteController.groupArr.clear();
        try {
            if (rs != null) {
                while (rs.next()) {
                    note.groupArr.add( rs.getString( "groupName" ) );
                }
                note.displayGroup();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void retrieveNote(String groupName){
        note.othersArr.clear();
        note.pinnedArr.clear();

        DB db=new DB();
        CachedRowSet rs=db.read("SELECT * FROM note WHERE groupName='"+groupName+"'");
        try {
            while(rs.next()){
                if(rs.getInt("isPined")>0){
                    note.pinnedArr.add(new Note(rs.getInt("noteID"),rs.getString("groupName"), rs.getString("title"), rs.getString("content"),true));

                }else{
                    note.othersArr.add(new Note(rs.getInt("noteID"),rs.getString("groupName"), rs.getString("title"), rs.getString("content"),false));
                }


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        note.displayNote();
    }
    public static void confirm() {
        DB.update(String.format("INSERT INTO groupFolder VALUES(\"%s\",\"%s\")",groupname.enteredGroupName.getText(), userID));
    }
    public static void save() {
        setPined(isPined);
        String sqlQuery = "UPDATE note SET content= '"+notepage.noteContent.getText()+"'  WHERE noteID="+getNoteID();

        DB.update(sqlQuery);
        int i=0;
        if(isPined) i=1;
        DB.update("UPDATE note SET isPined="+i+" WHERE noteID="+getNoteID());
    }*/
}
