package scene.event;

import resources.database.UserAccess;
import scene.event.UI.EventMainController;
import scene.event.UI.admin.EventManagerController;
import scene.event.entity.Event;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Liu Woon Kit on 5/7/2017.
 */
public class EventController {
    private static final String userID = UserAccess.getUser().getUserID();
    private static EventMainController eventMainController = null;
    private static EventManagerController eventManagerController = null;

    public static void setEventMainController(EventMainController e) {
        eventMainController = e;
    }

    public static void setEventManagerController(EventManagerController e) {
        eventManagerController = e;
    }

    public static String getOrganizerName(Event event) {
        return event.getOrganizerName();
    }

    public static ArrayList<Event> getEvents() {
        ArrayList<Event> eventArrayList = Event.getEvents(userID);
        Collections.sort(eventArrayList);
        return eventArrayList;
    }

    public static void updateUserEvent(Event event) {
        event.updateUserEvent(userID);
        onUpdate();
    }

    public static ArrayList<Event> getOrganizerEvents() {
        return Event.getOrganizerEvents(userID);
    }

    public static void createOrganizerEvent(String title, String desc, String location, Date date, Time startTime, Time endTime) {
        Event.createOrganizerEvent(title, desc, location, date, startTime, endTime, userID);
        onUpdate();
    }

    public static void updateOrganizerEvent(Event event) {
        event.updateOrganizerEvent();
        onUpdate();
    }

    public static void deleteOrganizerEvent(Event event) {
        event.deleteOrganizerEvent();
        onUpdate();
    }

    public static void onUpdate() {
        eventMainController.displayEvents();
        eventManagerController.displayOrganizerEvents();
    }
}