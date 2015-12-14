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


    public static DateTime JodaStringToDateTime(String stringDate, String pattern) {

        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        DateTime dateTime = fmt.parseDateTime(stringDate);

        return dateTime;
    }

    public static String JodaDateTimeToString(DateTime dateTime, String pattern) {
        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        String dateString = fmt.print(dateTime);

        return dateString;
    }

    public static String getNowString(String pattern) {

        return JodaDateTimeToString(new DateTime(), pattern);

    }
}
