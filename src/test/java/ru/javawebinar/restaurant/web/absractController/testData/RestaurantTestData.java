package ru.javawebinar.restaurant.web.absractController.testData;

import ru.javawebinar.restaurant.model.Dish;
import ru.javawebinar.restaurant.model.Restaurant;
import ru.javawebinar.restaurant.web.absractController.TestMatcher;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {
    public static TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Restaurant.class, "user", "dishes");
    public static TestMatcher<Restaurant> RESTAURANT_WITH_DISHES_MATCHER =
            TestMatcher.usingAssertions(Restaurant.class,
                    (a, e) -> assertThat(a).usingRecursiveComparison(),
                    (a, e) -> {
                        throw new UnsupportedOperationException();
                    });
    public static final int START_SEQ = 100000;
    public static final int RESTAURANT_ID_1 = START_SEQ + 2;
    public static final int NOT_FOUND = 10;
    public static final int USER = 100000;
    public static final int ADMIN = 100001;

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_ID_1, "Restaurant_1");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_ID_1 + 1, "Restaurant_2");
    public static final Restaurant RESTAURANT_3 = new Restaurant(RESTAURANT_ID_1 + 2, "Restaurant_3");
    public static final Restaurant RESTAURANT_4 = new Restaurant(RESTAURANT_ID_1 + 3, "Restaurant_4");
    public static final Restaurant RESTAURANT_5 = new Restaurant(RESTAURANT_ID_1 + 4, "Restaurant_5");

    public static final List<Restaurant> RESTAURANTS = List.of(RESTAURANT_5, RESTAURANT_4, RESTAURANT_3, RESTAURANT_2, RESTAURANT_1);
    public static final List<Restaurant> RESTAURANTS_FOR_USER = List.of(RESTAURANT_1, RESTAURANT_2, RESTAURANT_4);

    public static Restaurant getNew() {
        return new Restaurant(null, "Create Restaurant");
    }

    public static Restaurant getUpdate() {
        return new Restaurant(START_SEQ + 2, "Update Restaurant_1");
    }


}
