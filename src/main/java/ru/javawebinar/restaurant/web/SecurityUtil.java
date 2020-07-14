package ru.javawebinar.restaurant.web;



public class SecurityUtil {

    private static int id = 100_000;

    private SecurityUtil() {
    }

    public static int authUserId() {
        return id;
    }

    public static void setAuthUserId(int id) {
        ru.javawebinar.restaurant.web.SecurityUtil.id = id;
    }

}