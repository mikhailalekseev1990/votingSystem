package ru.javawebinar.restaurant.web.servlet;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;
import ru.javawebinar.restaurant.model.Restaurant;
import ru.javawebinar.restaurant.web.DishController;
import ru.javawebinar.restaurant.web.RestaurantController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class RestaurantServlet extends HttpServlet {

    ConfigurableApplicationContext springContext;
    RestaurantController restaurantController;
    DishController dishController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
        restaurantController = springContext.getBean(RestaurantController.class);
        dishController = springContext.getBean(DishController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");


        switch (action == null ? "all" : action) {
            case "all":
            default:
//                request.setAttribute("restaurants", restaurantController.getAll());
                request.setAttribute("dishes", dishController.getAll());
                request.getRequestDispatcher("/restaurants.jsp").forward(request, response);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Restaurant restaurant = new Restaurant(request.getParameter("restName"));
//        if (StringUtils.isEmpty(request.getParameter("id"))) {
//            restaurantController.create(restaurant);
//        } else {
//            restaurantController.update(restaurant, getId(request));
//        }
        response.sendRedirect("restaurants");
    }

    private int getId(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("id"));
    }
}
