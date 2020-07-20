package ru.javawebinar.restaurant.web;



public class SecurityUtil {

    private static int id = 100_000;

    private SecurityUtil() {
    }

    public static int authu_id() {
        return id;
    }

    public static void setAuthu_id(int id) {
        ru.javawebinar.restaurant.web.SecurityUtil.id = id;
    }

}