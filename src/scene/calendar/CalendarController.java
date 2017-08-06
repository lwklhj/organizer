package scene.calendar;

import resources.database.UserAccess;
import scene.Task.entity.Task;
import scene.calendar.UI.AgendaController;
import scene.event.entity.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by Liu Woon Kit on 5/7/2017.
 */
public class CalendarController {
    private static AgendaController agendaController = null;
    private static String userID = UserAccess.getUser().getUserID();

    public static void setAgendaController(AgendaController a) {
        agendaController = a;
    }

    public static void showAgenda(GregorianCalendar gregorianCalendar) {
        agendaController.getTimes(gregorianCalendar);
    }

    // yyyy-mm-dd
    public static String dateFormat(GregorianCalendar gregorianCalendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setCalendar(gregorianCalendar);
        return simpleDateFormat.format(gregorianCalendar.getTime());
    }

    public static int getNumOfEvents(GregorianCalendar gregorianCalendar) {
        return Event.getNumOfEventsOfDate(userID, dateFormat(gregorianCalendar));
    }

    public static ArrayList<Event> getEventsOfDate(GregorianCalendar gregorianCalendar) {
        return Event.getEventsOfDate(userID, dateFormat(gregorianCalendar));
    }

    public static int getNumOfTasks(GregorianCalendar gregorianCalendar) {
        return 1;
    }

    public static ArrayList<Task> getTaskOfDate(GregorianCalendar gregorianCalendar) {
        return null;
    }
}
