package ru.javawebinar.restaurant.Utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private TimeUtil() {
    }

    public static LocalDateTime deadLine(LocalDateTime localDateTime) {
        return LocalDateTime.of(localDateTime.getYear(), localDateTime.getMonth(), localDateTime.getDayOfMonth(), 14, 0);
    }

    public static boolean isNewDay(LocalDateTime voteTime, LocalDateTime now){
        return isCompared(voteTime.toLocalDate() , now.toLocalDate());
    }

    public static <T extends Comparable<T>> boolean isCompared(T value, T deadLine) {
        return value.compareTo(deadLine) <= 0;
    }


}
