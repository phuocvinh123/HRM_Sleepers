package utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static LocalDate parseDate(String StrDate) {
        try {
            return LocalDate.parse(StrDate, dateTimeFormatter);
        } catch (DateTimeException dateTimeException) {
            dateTimeException.printStackTrace();
        }
        return null;
    }

    public static String formatDate(LocalDate localDate) {
        try {
            return dateTimeFormatter.format(localDate);
        } catch (DateTimeException dateTimeException) {
            dateTimeException.printStackTrace();
        }

        return null;
    }
}
