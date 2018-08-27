package framework.jimmy.com.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String DATE_TIME_FULL_FORMAT = "yyyy-MM-dd HH:mm:ss.fff";
    public static final String TIME_FULL_FORMAT = "HH:mm:ss.fff";

    public static Date toDate(String dateValue) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
        try {
            Date date = format.parse(dateValue);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toString(Date dateValue) {
        if(dateValue != null) {
            SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
            return format.format(dateValue);
        }
        return "";
    }
}
