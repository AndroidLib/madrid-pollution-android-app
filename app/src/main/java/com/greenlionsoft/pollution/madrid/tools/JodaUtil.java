package com.greenlionsoft.pollution.madrid.tools;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class JodaUtil {

    /**
     * Private constructor for utility class
     */
    private JodaUtil() {
    }


    public static DateTime JodaStringToDateTime(String stringDate) {

        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dateTime = fmt.parseDateTime(stringDate);

        return dateTime;
    }

    public static String JodaDateTimeToString(DateTime dateTime) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        String dateString = fmt.print(dateTime);

        return dateString;
    }

    public static String getNowString() {

        return JodaDateTimeToString(new DateTime());

    }
}
