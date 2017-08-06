package scene.event.entity;

import resources.database.DB;

import javax.sql.rowset.CachedRowSet;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by Liu Woon Kit on 12/6/2017.
 */
public class Event implements Comparable<Event> {
    private int id;
    private String title;
    private String desc;
    private String location;
    private GregorianCalendar date;
    private Time startTime;
    private Time endTime;

    private String status; // Not approved, waiting for approval, approved...etc
    private boolean isRegistered;

    public Event(int id, String title, String desc, String location, Date date, Time startTime, Time endTime) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.location = location;
        this.date = new GregorianCalendar();
        this.date.setTime(date);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(int id, String title, String desc, String location, Date date, Time startTime, Time endTime, boolean isRegistered) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.location = location;
        this.date = new GregorianCalendar();
        this.date.setTime(date);
        this.startTime = startTime;
        this.endTime = endTime;
        this.isRegistered = isRegistered;
    }

    public Event(int id, String title, String desc, String location, Date date, Time startTime, Time endTime, String status) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.location = location;
        this.date = new GregorianCalendar();
        this.date.setTime(date);
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date.setTime(date);
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public static ArrayList<Event> getEvents(String userID) {
        ArrayList<Event> eventArrayList = new ArrayList<>();
        CachedRowSet rs = DB.read("SELECT e.eventID, e.eventTitle, e.eventDesc, e.location, e.date, e.startTime, e.endTime, ue.userID FROM Events e LEFT JOIN UserEvents ue ON e.eventID = ue.eventID WHERE ue.userID = '"+userID+"' OR ue.userID IS NULL;");
        try {
            while (rs.next()) {
                eventArrayList.add(new Event(rs.getInt("eventID"), rs.getString("eventTitle"), rs.getString("eventDesc"), rs.getString("location"), rs.getDate("date"), rs.getTime("startTime"), rs.getTime("endTime"), rs.getString("userID")==null ? false : true));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventArrayList;
    }

    public void updateUserEvent(String userID) {
        if(!isRegistered()) {
            DB.update("DELETE FROM UserEvents WHERE userID = '"+userID+"' && eventID = '"+id+"';");
        }
        else {
            DB.update("INSERT INTO UserEvents VALUES('"+id+"', '"+userID+"');");
        }
    }

    public static ArrayList<Event> getOrganizerEvents(String userID) {
        ArrayList<Event> eventArrayList = new ArrayList<>();
        CachedRowSet rs = DB.read("SELECT eventID, eventTitle, eventDesc, location, date, startTime, endTime, status FROM Events WHERE organizerID = '"+userID+"'");
        try {
            while(rs.next()) {
                eventArrayList.add(new Event(rs.getInt("eventID"), rs.getString("eventTitle"), rs.getString("eventDesc"), rs.getString("location"), rs.getDate("date"), rs.getTime("startTime"), rs.getTime("endTime"), rs.getString("status")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventArrayList;
    }

    public static void createOrganizerEvent(String title, String desc, String location, Date date, Time startTime, Time endTime, String userID) {
        DB.update("INSERT INTO Events VALUES('"+title+"', NULL, '"+desc+"', '"+location+"', '"+date+"', '"+startTime+"', '"+endTime+"', 0,'"+userID+"')");
    }

    public void updateOrganizerEvent() {
        DB.update("UPDATE Events SET eventTitle = '"+title+"', eventDesc= '"+desc+"', location = '"+location+"', date = '"+new Date(date.getTimeInMillis())+"', startTime = '"+startTime+"', endTime = '"+endTime+"'  WHERE eventID = '"+id+"' ");
    }

    public void deleteOrganizerEvent() {
        DB.update("DELETE FROM Events WHERE eventID = '"+id+"' ");
    }

    public String getOrganizerName() {
        CachedRowSet rs = DB.read("SELECT name FROM User WHERE userID = (SELECT organizerID FROM Events WHERE eventID = '"+id+"')");
        try {
            if(rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getNumOfEventsOfDate(String userID, String date) {
        CachedRowSet rs = DB.read("SELECT count(*) FROM Events e INNER JOIN UserEvents ue ON e.eventID = ue.eventID WHERE date='"+date+"' && userID = '"+userID+"'");

        try {
            if(rs.next()) {
                return rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static ArrayList<Event> getEventsOfDate(String userID, String date) {
        ArrayList<Event> events = new ArrayList<>();
        CachedRowSet rs = DB.read("SELECT * FROM Events e INNER JOIN UserEvents ue ON e.eventID = ue.eventID WHERE date='"+date+"' && userID = '"+userID+"'");
        try {
            while(rs.next()) {
                events.add(new Event(rs.getInt("eventID"), rs.getString("eventTitle"), rs.getString("eventDesc"), rs.getString("location"), rs.getDate("date"), rs.getTime("startTime"), rs.getTime("endTime")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }


    @Override
    public int compareTo(Event e) {
        return -1 * this.date.compareTo(e.date);
    }
}