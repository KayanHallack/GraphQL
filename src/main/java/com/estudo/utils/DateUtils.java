package com.estudo.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static com.estudo.configs.ApplicationConfig.DATE_PATTERN;

public class DateUtils {

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static String parseDate(ZonedDateTime date) {
        try {
            return date.format(dateFormat);
        } catch (Exception e) {
            return null;
        }
    }

    public static ZonedDateTime parseString(String date) {
        try {
            return ZonedDateTime.parse(date, dateFormat);
        } catch (Exception e) {
            return null;
        }
    }
}
