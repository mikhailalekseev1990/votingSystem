package ru.javawebinar.restaurant.web.jspController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.javawebinar.restaurant.repository.RestaurantRepository;
import ru.javawebinar.restaurant.web.SecurityUtil;
import ru.javawebinar.restaurant.web.absractController.AbstractUserController;

import javax.servlet.http.HttpServletRequest;

@Controller
public class JspUserController extends AbstractUserController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", super.getAll());
        return "users";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int u_id = Integer.parseInt(request.getParameter("u_id"));
        SecurityUtil.setAuthu_id(u_id);
        return "redirect:restaurants";
    }

}
