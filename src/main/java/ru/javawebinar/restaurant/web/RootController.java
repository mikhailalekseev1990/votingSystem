package ru.javawebinar.restaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.restaurant.model.Role;
import ru.javawebinar.restaurant.service.UserService;
import ru.javawebinar.restaurant.web.absractController.AbstractRestaurantController;
import ru.javawebinar.restaurant.web.security.SecurityUtil;

import java.util.Set;

@Controller
public class RootController extends AbstractRestaurantController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/")
    public String root() {
        Set<Role> roles = userService.get(SecurityUtil.authUserId()).getRoles();
        return roles.contains(Role.ADMIN) ? "redirect:users" : "redirect:restaurants";
    }

    @GetMapping(value = "/restaurants")
    public String restaurantList(Model model) {
        model.addAttribute("user", super.getUser(SecurityUtil.authUserId()));
        model.addAttribute("restaurants", super.getAllWithDishes(SecurityUtil.authUserId()));
        return "restaurants";
    }

    @GetMapping(value = "/login")
    public String login(ModelMap model,
                        @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "message", required = false) String message) {
        model.put("error", error);
        model.put("message", message);
        return "login";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }
}
