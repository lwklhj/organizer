package scene.calendar;

import scene.calendar.UI.AgendaController;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Created by Liu Woon Kit on 5/7/2017.
 */
public class CalendarController {
    private static AgendaController agendaController = null;

    public static void setAgendaController(AgendaController a) {
        agendaController = a;
    }

    // Request agenda scene to retrieve and display events of stated date
    public static void showAgenda(GregorianCalendar gregorianCalendar) {
        agendaController.getTimes(gregorianCalendar);
    }

    // yyyy-mm-dd
    public static String dateFormat(GregorianCalendar gregorianCalendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setCalendar(gregorianCalendar);
        return simpleDateFormat.format(gregorianCalendar.getTime());
    }
}
