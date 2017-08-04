package scene.calendar.entity;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Created by Liu Woon Kit on 23/11/2016.
 */
public class CustomCalendar {
    private GregorianCalendar date = new GregorianCalendar();
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    private final GregorianCalendar CURRENT_DATE = date;

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public GregorianCalendar getCURRENT_DATE() {
        return CURRENT_DATE;
    }

    public int getSelectedDay() {
        return date.get(GregorianCalendar.DAY_OF_MONTH);
    }

    public int getSelectedMonth() {
        return date.get(GregorianCalendar.MONTH);
    }

    public int getSelectedYear() {
        return date.get(GregorianCalendar.YEAR);
    }

    public int getFirstDayOfFirstWeek() {
        date.set(GregorianCalendar.DAY_OF_MONTH, 1);
        return date.get(GregorianCalendar.DAY_OF_WEEK);
    }

    public int getMaxDaysInMonth() {
        return date.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
    }

    public String formatDate(String format) {
        simpleDateFormat.applyPattern(format);
        return simpleDateFormat.format(date.getTime());
    }

    public void manipulateMth(int i) {
        date.add(GregorianCalendar.MONTH, i);
    }

    // yyyy-mm-dd
    public static String dateFormat(GregorianCalendar gregorianCalendar) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setCalendar(gregorianCalendar);
        return simpleDateFormat.format(gregorianCalendar.getTime());
    }
}