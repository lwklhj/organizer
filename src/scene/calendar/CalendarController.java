package scene.calendar;

import resources.database.DB;
import resources.database.UserDAO;
import scene.calendar.UI.AgendaController;
import scene.event.entity.Event;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by Liu Woon Kit on 5/7/2017.
 */
public class CalendarController {
    private static AgendaController agendaController = null;
    private static String  userID = UserDAO.getUser().getUserID();

    public static int getNumOfEvents(GregorianCalendar gregorianCalendar) {
        CachedRowSet rs = DB.read("SELECT * FROM Events e INNER JOIN UserEvents ue ON e.eventID = ue.eventID WHERE date='"+dateFormat(gregorianCalendar)+"' && userID = '"+userID+"'");
        int i = 0;
        try {
            while(rs.next()) {
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(i);
        return i;
    }

    public static int getNumOfTasks(GregorianCalendar gregorianCalendar) {
        return 1;
    }

    // yyyy-mm-dd
    public static String dateFormat(GregorianCalendar gregorianCalendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setCalendar(gregorianCalendar);
        return simpleDateFormat.format(gregorianCalendar.getTime());
    }

    public static void setAgendaController(AgendaController a) {
        agendaController = a;
    }

    public static void showAgenda(GregorianCalendar gregorianCalendar) {
        agendaController.getTimes(gregorianCalendar);
    }

    public static ArrayList<Event> getEvents(GregorianCalendar gregorianCalendar) {
        ArrayList<Event> events = new ArrayList<>();
        CachedRowSet rs = DB.read("SELECT * FROM Events e INNER JOIN UserEvents ue ON e.eventID = ue.eventID WHERE date='"+dateFormat(gregorianCalendar)+"' && userID = '"+userID+"'");
        try {
            while(rs.next()) {
                events.add(new Event(rs.getInt("eventID"), rs.getString("eventTitle"), rs.getString("eventDesc"), rs.getString("location"), rs.getDate("date"), rs.getTime("startTime"), rs.getTime("endTime")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }


}
