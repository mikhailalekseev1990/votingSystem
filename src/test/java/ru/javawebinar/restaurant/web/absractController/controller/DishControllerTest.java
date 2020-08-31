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
import ru.javawebinar.restaurant.model.Dish;
import ru.javawebinar.restaurant.repository.DishRepository;
import ru.javawebinar.restaurant.web.absractController.testData.DishTestData;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static ru.javawebinar.restaurant.Utils.ValidationUtil.checkNotFoundWithId;
import static ru.javawebinar.restaurant.web.absractController.testData.DishTestData.*;
import static ru.javawebinar.restaurant.web.absractController.testData.RestaurantTestData.NOT_FOUND;
import static ru.javawebinar.restaurant.web.absractController.testData.RestaurantTestData.RESTAURANT_ID_1;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:DB/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class DishControllerTest {

    @Autowired
    private DishRepository dishRepository;

    @Test
    public void get() {
        Dish actual = dishRepository.get(DISH_ID_1, RESTAURANT_ID_1);
        DISH_MATCHER.assertMatch(actual, DISH_1);
    }

    @Test
    public void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(dishRepository.get(NOT_FOUND, RESTAURANT_ID_1), NOT_FOUND));
    }

    @Test
    public void getNotOwn() throws Exception {
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(dishRepository.get(DISH_ID_1, RESTAURANT_ID_1 + 1), DISH_ID_1));
    }

    @Test
    public void getAll() {
        DISH_MATCHER.assertMatch(dishRepository.getAll(RESTAURANT_ID_1), DISHES_FOR_RESTAURANT_1);
    }

    @Test
    public void create() {
        Dish created = dishRepository.save(DishTestData.getNew(), RESTAURANT_ID_1);
        int newId = created.id();
        Dish newDish = DishTestData.getNew();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishRepository.get(newId, RESTAURANT_ID_1), newDish);
    }

    @Test
    public void update() {
        Dish updated = DishTestData.getUpdate();
        dishRepository.save(updated, RESTAURANT_ID_1);
        DISH_MATCHER.assertMatch(dishRepository.get(DISH_ID_1, RESTAURANT_ID_1), DishTestData.getUpdate());
    }

    @Test
    public void updateNotOwn() throws Exception {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> checkNotFoundWithId(dishRepository.save(DISH_1, RESTAURANT_ID_1+1), DISH_ID_1));
        String exceptionMessage = exception.getMessage();
        assertTrue(exceptionMessage.contains(ErrorType.DATA_NOT_FOUND.name()));
        assertTrue(exceptionMessage.contains(NotFoundException.NOT_FOUND_EXCEPTION));
        assertTrue(exceptionMessage.contains(String.valueOf(DISH_ID_1)));
    }

    @Test
    public void delete() throws Exception {
        dishRepository.delete(DISH_ID_1, RESTAURANT_ID_1);
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(dishRepository.get(DISH_ID_1, RESTAURANT_ID_1), DISH_ID_1));
    }

    @Test
    public void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(dishRepository.delete(NOT_FOUND, RESTAURANT_ID_1), NOT_FOUND));
    }

    @Test
    public void deleteNotOwn() throws Exception {
        assertThrows(NotFoundException.class, () -> checkNotFoundWithId(dishRepository.delete(DISH_ID_1, RESTAURANT_ID_1 + 1), NOT_FOUND));
    }
}