package scene.note.entity;

import resources.database.DB;
import resources.database.UserAccess;

public class Note {
    private String adminNo = UserAccess.getUser().getUserID();
    private String group;
    private String title;
    private String content;
    private boolean isPined;
    private int noteID;
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

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }
}
