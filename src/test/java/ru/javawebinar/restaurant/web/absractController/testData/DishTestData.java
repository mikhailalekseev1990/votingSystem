package ru.javawebinar.restaurant.web.absractController.testData;

import ru.javawebinar.restaurant.model.Dish;
import ru.javawebinar.restaurant.web.absractController.TestMatcher;

import java.util.List;

public class DishTestData {
    public static TestMatcher<Dish> DISH_MATCHER = TestMatcher.usingFieldsComparator("restaurant");

    public static final int DISH_ID_1 = 100007;


    public static final Dish DISH_1 = new Dish(DISH_ID_1, "dish_1_1", 1000);
    public static final Dish DISH_2 = new Dish(DISH_ID_1 + 1, "dish_2_1", 1001);
    public static final Dish DISH_3 = new Dish(DISH_ID_1 + 2, "dish_3_1", 1002);
    public static final Dish DISH_4 = new Dish(DISH_ID_1 + 3, "dish_1_2", 1010);
    public static final Dish DISH_5 = new Dish(DISH_ID_1 + 4, "dish_2_2", 1011);
    public static final Dish DISH_6 = new Dish(DISH_ID_1 + 5, "dish_3_2", 1012);
    public static final Dish DISH_7 = new Dish(DISH_ID_1 + 6, "dish_1_3", 1110);
    public static final Dish DISH_8 = new Dish(DISH_ID_1 + 7, "dish_2_3", 1111);
    public static final Dish DISH_9 = new Dish(DISH_ID_1 + 8, "dish_3_3", 1112);
    public static final Dish DISH_10 = new Dish(DISH_ID_1 + 9, "dish_1_4", 1110);
    public static final Dish DISH_11 = new Dish(DISH_ID_1 + 10, "dish_2_4", 1111);
    public static final Dish DISH_12 = new Dish(DISH_ID_1 + 11, "dish_1_5", 1113);
    public static final Dish DISH_13 = new Dish(DISH_ID_1 + 12, "dish_2_5", 1114);

    public static final List<Dish> DISHES = List.of(DISH_13, DISH_12, DISH_11, DISH_10, DISH_9, DISH_8, DISH_7, DISH_6, DISH_5, DISH_4, DISH_3, DISH_2, DISH_1);
    public static final List<Dish> DISHES_FOR_REST_1 = List.of( DISH_1, DISH_2, DISH_3);

    public static Dish getNew() {
        return new Dish(null, "Create Dish", 99);
    }

    public static Dish getUpdate() {
        return new Dish(DISH_ID_1, "Update Dish_1", 999);
    }

}
