package ru.javawebinar.restaurant.Utils;

import java.time.LocalTime;

public class TimeUtil {
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
