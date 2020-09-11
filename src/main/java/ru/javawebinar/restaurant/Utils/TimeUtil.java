package ru.javawebinar.restaurant.Utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final LocalTime deadLine = LocalTime.parse("11:00");

    private TimeUtil() {
    }

    public static boolean isVoteTime(LocalTime now) {
        return isCompared(now, deadLine);
    }

    public static <T extends Comparable<T>> boolean isCompared(T value, T deadLine) {
        return value.compareTo(deadLine) <= 0;
    }

}
