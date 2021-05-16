package de.malik.mylibrary.managers;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeManager {

    /**
     * the separator of a time. For example 12<underlined>:</underlined>20 Uhr
     */
    public static final String DEFAULT_TIME_SEPARATOR = ":";

    /**
     * the separator of a date. For example 12<underlined>.</underlined>05<underlined>.</underlined>2021
     */
    public static final String DEFAULT_DATE_SEPARATOR = ".";

    /**
     * the format in which the dates will be shown. For example 11.05.2021
     */
    public static final String DEFAULT_DATE_FORMAT = "dd.MM.yyyy";

    /**
     * the format in which the times will be shown. For example 1:15:16
     */
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

    /**
     * the suffix which will be used for times. For example 12:20 <underlined>Uhr</underlined>
     */
    public static final String DEFAULT_TIME_SUFFIX = " Uhr";

    /**
     * an Array containing all the week days starting with "Montag" and ending with "Sonntag"
     */
    public static final String[] WEEK_DAYS = new String[] {
            "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"
    };

    /**
     * the conversion factor from milliseconds to days
     */
    private static final int DAYS = 86400000;

    /**
     * the conversion factor from milliseconds to hours
     */
    public static final int HOURS = 3600000;

    /**
     * the conversion factor from milliseconds to minutes
     */
    public static final int MINUTES = 60000;

    /**
     * the conversion factor from milliseconds to seconds
     */
    public static final int SECONDS = 1000;

    public static String default_time_separator = DEFAULT_TIME_SEPARATOR;
    public static String default_date_separator = DEFAULT_DATE_SEPARATOR;
    public static String default_date_format = DEFAULT_DATE_FORMAT;
    public static String default_time_suffix = DEFAULT_TIME_SUFFIX;

    /**
     * creates an array of long containing: 0 = hours; 1 = minutes; 2 = seconds.
     * @param timeString the time String which will be separated
     * @return an array of long with all the parts of the time String
     */
    public static long[] timeParts(@NonNull String timeString) {
        String[] parts = timeString.split(default_time_separator);
        long hours = Integer.parseInt(parts[0]);
        long minutes = Integer.parseInt(parts[1]);
        long seconds = Integer.parseInt(parts[2]);
        return new long[] {hours, minutes, seconds};
    }

    /**
     * creates a formatted date String. For example: day = 1, month = 5, year = 2021 will be
     * converted to a String:
     * 01<code>default_time_separator</code>05<code>default_time_separator</code>2021.
     * @param day the day of the date
     * @param month the month of the date
     * @param year the year of the date
     * @return a formatted date String
     */
    public static String formatDate(int day, int month, int year) {
        String dayString = day + "", monthString = month + "";
        if (day < 10) {
            dayString = "0" + dayString;
        }
        if (month < 10) {
            monthString = "0" + monthString;
        }
        return dayString + default_date_separator + monthString + default_date_separator + year;
    }

    /**
     * creates a formatted date String. For example: a date String
     * 2<code>default_time_separator</code>5<code>default_time_separator</code>2021 will be
     * converted to 02.05.2021.
     * @param dateString the date String which will be formatted
     * @return a formatted date String
     */
    public static String formatDateString(String dateString) {
        int[] parts = dateParts(dateString);
        String day = parts[0] + "", month = parts[1] + "";
        if (parts[0] < 10) {
            day = "0" + day;
        }
        if (parts[1] < 10) {
            month = "0" + month;
        }
        return day + default_date_separator + month + default_date_separator + parts[2];
    }

    /**
     * divides the date String into its parts. for example 12.05.2021 will be split into 12, 5 and 2021
     * @param dateString the date String which will be split
     * @return a date String divided into its parts
     */
    public static int[] dateParts(String dateString) {
        String[] parts = dateString.split(default_date_separator);
        return new int[] {Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])};
    }

    /**
     * creates a String with the current date
     * @return a String with the current date
     */
    public static String currentDate() {
        DateFormat df = new SimpleDateFormat(default_date_format, Locale.getDefault());
        df.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
        return df.format(new Date());
    }

    /**
     * creates a String with the current time and the <code>default_time_suffix</code> as suffix
     * @return the current time with the <code>default_time_suffix</code> as suffix
     */
    public static String currentTime() {
        DateFormat df = new SimpleDateFormat(DEFAULT_TIME_FORMAT, Locale.getDefault());
        df.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
        return df.format(new Date()) + default_time_suffix;
    }

    /**
     * takes the day of month of the date String and finds the belonging day of week
     * @param dateString the date String of which the day of week will be found
     * @return a int which will be the number of the day of the week. use it in combination with the
     *         <code>WEEK_DAYS</code> array to find out the day name.
     * @throws ParseException method may throws a ParseException if the date String is not well
     *                        formatted
     */
    public static int dayOfWeek(@NonNull String dateString) throws ParseException {
        Calendar c = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat(default_date_format, Locale.getDefault());
        df.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(df.parse(dateString));
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * takes a time String and converts it to milliseconds
     * @param timeString the time String which will be converted
     * @return the time String argument as milliseconds
     */
    public static long toMillis(@NonNull String timeString) {
        long[] timeParts = timeParts(timeString);
        long hoursMillis = timeParts[0] * HOURS;
        long minutesMillis = timeParts[1] * MINUTES;
        long secondsMillis = timeParts[2] * SECONDS;
        return hoursMillis + minutesMillis + secondsMillis;
    }

    /**
     * converts the given time into the parts (hours, minutes, seconds) and creates a formatted
     * String
     * @param timeArg the time which will be formatted
     * @param includeSeconds if true, the String will contain the seconds, otherwise it will not
     * @return a formatted String containing the hours, minutes and maybe seconds. The String will
     *         be divided by <code>default_time_separator</code>
     */
    public static String toTimeString(@NonNull Date timeArg, boolean includeSeconds) {
        long millis = timeArg.getTime();
        long hours = millis % DAYS / HOURS;
        long minutes = millis % DAYS % HOURS / MINUTES;
        long seconds = millis % DAYS % HOURS % MINUTES / SECONDS;
        return formatValues(hours, minutes, seconds, includeSeconds);
    }

    /**
     * puts all arguments (<code>hours</code>, <code>minutes</code> and <code>seconds</code>)
     * together into a String and returns it as a formatted String
     * @param hours the hours of the time
     * @param minutes the minutes of the time
     * @param seconds the seconds of the time
     * @param includeSeconds if true, the String will contain the seconds, otherwise it will not
     * @return a formatted String containing the hours, minutes and maybe seconds. The String will
     *         be divided by <code>default_time_separator</code>
     */
    private static String formatValues(long hours, long minutes, long seconds, boolean includeSeconds) {
        String hoursString = "" + hours, minutesString = "" + minutes, secondsString = "" + seconds;
        if (default_time_separator == null || default_time_separator.equals(""))
            default_time_separator = DEFAULT_TIME_SEPARATOR;
        if (includeSeconds)
            return hoursString + default_time_separator + minutesString + default_time_separator + secondsString;
        return hoursString + default_time_separator + minutesString;
    }

    /**
     * gets the time of the two dates in milli seconds and subtracts <code>d2</code> from
     * <code>d1</code>
     * @param d1 the first time minuend
     * @param d2 the second time subtrahend
     * @return the difference from d1 and d2 in milli seconds
     */
    public static long diff(@NonNull Date d1, @NonNull Date d2) {
        return d1.getTime() - d2.getTime();
    }
}
