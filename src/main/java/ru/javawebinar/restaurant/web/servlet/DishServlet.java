package ru.javawebinar.restaurant.web.servlet;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;
import ru.javawebinar.restaurant.model.Dish;
import ru.javawebinar.restaurant.web.absractController.AbstractDishController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class DishServlet extends HttpServlet {
    ConfigurableApplicationContext springContext;
    AbstractDishController dishController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
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
        Dish dish = new Dish(request.getParameter("nameDish"), Integer.parseInt(request.getParameter("price")));
        String d_id = request.getParameter("d_id");
        if (StringUtils.isEmpty(d_id)) {
            dishController.create(dish, getRestaurantId(request));
        } else {
            dishController.update(dish, getDishId(request), getRestaurantId(request));
        }
        response.sendRedirect("restaurants");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("dish_action");

        switch (action) {
            case "delete" -> {
                int d_id = getDishId(request);
                int r_id = getRestaurantId(request);
                dishController.delete(d_id, r_id);
                response.sendRedirect("restaurants");
            }
            case "create", "update" -> {
                final Dish dish = "create".equals(action) ?
                        new Dish("", 0) :
                        dishController.get(getDishId(request), getRestaurantId(request));
                request.setAttribute("restaurantId", getRestaurantId(request));
                request.setAttribute("dish", dish);
                request.getRequestDispatcher("/dishForm.jsp").forward(request, response);
            }
//            default -> {
//                request.setAttribute("restaurants", dishController.getAll(getr_id(request)));
//                request.getRequestDispatcher("/restaurantForm.jsp").forward(request, response);
//            }
        }

    }

    private int getDishId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("d_id"));
        return Integer.parseInt(paramId);
    }
    private int getRestaurantId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("r_id"));
        return Integer.parseInt(paramId);
    }
}
