package ru.javawebinar.restaurant.web.jspController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javawebinar.restaurant.model.Restaurant;
import ru.javawebinar.restaurant.repository.RestaurantRepository;
import ru.javawebinar.restaurant.web.SecurityUtil;
import ru.javawebinar.restaurant.web.absractController.AbstractUserController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class JspUserController extends AbstractUserController {
    Logger LOG = LoggerFactory.getLogger(JspUserController.class);
    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", super.getAll());
        return "users";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        return "redirect:restaurants";
    }

}
