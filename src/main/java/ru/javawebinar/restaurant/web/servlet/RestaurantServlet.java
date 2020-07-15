package ru.javawebinar.restaurant.web.servlet;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;
import ru.javawebinar.restaurant.model.Restaurant;
import ru.javawebinar.restaurant.web.DishController;
import ru.javawebinar.restaurant.web.absractController.AbstractDishController;
import ru.javawebinar.restaurant.web.absractController.AbstractRestaurantController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class RestaurantServlet extends HttpServlet {

    ConfigurableApplicationContext springContext;
    AbstractRestaurantController restaurantController;
    AbstractDishController dishController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        restaurantController = springContext.getBean(AbstractRestaurantController.class);
        dishController = springContext.getBean(AbstractDishController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Restaurant restaurant = new Restaurant(request.getParameter("name"));
        if (StringUtils.isEmpty(request.getParameter("id"))) {
            restaurantController.create(restaurant);
        } else {
            restaurantController.update(restaurant, getId(request));
        }
        response.sendRedirect("restaurants");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete" -> {
                int id = getId(request);
                restaurantController.delete(id);
                response.sendRedirect("restaurants");
            }
            case "create", "update" -> {
                final Restaurant restaurant = "create".equals(action) ?
                        new Restaurant("") :
                        restaurantController.get(getId(request));
                request.setAttribute("restaurant", restaurant);
                request.getRequestDispatcher("/restaurantForm.jsp").forward(request, response);
            }
            default -> {
                request.setAttribute("restaurants", restaurantController.getAll());
                request.getRequestDispatcher("/restaurants.jsp").forward(request, response);
            }
        }

    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}