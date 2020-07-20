package ru.javawebinar.restaurant.web.servlet;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;
import ru.javawebinar.restaurant.model.Restaurant;
import ru.javawebinar.restaurant.web.absractController.AbstractDishController;
import ru.javawebinar.restaurant.web.absractController.AbstractRestaurantController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        String r_id = request.getParameter("r_id");
        if (StringUtils.isEmpty(r_id)) {
            restaurantController.create(restaurant);
        } else {
            restaurantController.update(restaurant, getRestaurantId(request));
        }
        response.sendRedirect("restaurants");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete" -> {
                int id = getRestaurantId(request);
                restaurantController.delete(id);
                response.sendRedirect("restaurants");
            }
            case "create", "update" -> {
                final Restaurant restaurant = "create".equals(action) ?
                        new Restaurant("") :
                        restaurantController.get(getRestaurantId(request));
                request.setAttribute("restaurant", restaurant);
                request.getRequestDispatcher("/restaurantForm.jsp").forward(request, response);
            }
            default -> {
//                int r_id = Integer.parseInt(request.getParameter("r_id"));
                request.setAttribute("restaurants", restaurantController.getAll());
//                request.setAttribute("dishes", restaurantController.get(100_003).getDishes());
                request.getRequestDispatcher("/restaurants.jsp").forward(request, response);
            }
        }

    }

    private int getRestaurantId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("r_id"));
        return Integer.parseInt(paramId);
    }
}