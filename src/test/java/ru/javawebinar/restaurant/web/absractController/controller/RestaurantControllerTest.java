package ru.javawebinar.restaurant.web.absractController.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.restaurant.Utils.exception.ErrorType;
import ru.javawebinar.restaurant.Utils.exception.NotFoundException;
import ru.javawebinar.restaurant.model.Restaurant;
import ru.javawebinar.restaurant.repository.RestaurantRepository;
import ru.javawebinar.restaurant.web.absractController.testData.RestaurantTestData;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static ru.javawebinar.restaurant.Utils.ValidationUtil.checkNotFoundWithId;
import static ru.javawebinar.restaurant.web.absractController.testData.RestaurantTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:DB/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantControllerTest {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    public void get() {
        Restaurant actual = restaurantRepository.get(RESTAURANT_ID_1, USER_ID);
        RESTAURANT_MATCHER.assertMatch(actual, RESTAURANT_1);
    }

    @Test
    public void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(restaurantRepository.get(NOT_FOUND, USER_ID), NOT_FOUND));
    }

    @Test
    public void getNotOwn() throws Exception {
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(restaurantRepository.get(RESTAURANT_ID_1, RESTAURANT_ADMIN_ID), RESTAURANT_ID_1));
    }

    @Test
    public void getAll() {
        RESTAURANT_MATCHER.assertMatch(restaurantRepository.getAll(USER_ID), RESTAURANTS_FOR_USER);
    }

    @Test
    public void create() {
        Restaurant created = restaurantRepository.save(RestaurantTestData.getNew(), USER_ID);
        int newId = created.id();
        Restaurant newRestaurant = RestaurantTestData.getNew();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(restaurantRepository.get(newId, USER_ID), newRestaurant);
    }

    @Test
    public void update() {
        Restaurant updated = RestaurantTestData.getUpdate();
        restaurantRepository.save(updated, USER_ID);
        RESTAURANT_MATCHER.assertMatch(restaurantRepository.get(RESTAURANT_ID_1, USER_ID), RestaurantTestData.getUpdate());
    }

    @Test
    public void updateNotOwn() throws Exception {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> checkNotFoundWithId(restaurantRepository.save(RESTAURANT_1, RESTAURANT_ADMIN_ID), RESTAURANT_ID_1));
        String exceptionMessage = exception.getMessage();
        assertTrue(exceptionMessage.contains(ErrorType.DATA_NOT_FOUND.name()));
        assertTrue(exceptionMessage.contains(NotFoundException.NOT_FOUND_EXCEPTION));
        assertTrue(exceptionMessage.contains(String.valueOf(RESTAURANT_ID_1)));
    }


    @Test
    public void delete() throws Exception {
        restaurantRepository.delete(RESTAURANT_ID_1, USER_ID);
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(restaurantRepository.get(RESTAURANT_ID_1, USER_ID), RESTAURANT_ID_1));
    }

    @Test
    public void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(restaurantRepository.delete(NOT_FOUND, USER_ID), NOT_FOUND));
    }

    @Test
    public void deleteNotOwn() throws Exception {
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(restaurantRepository.delete(RESTAURANT_ID_1, RESTAURANT_ADMIN_ID), NOT_FOUND));
    }

}
