package ru.javawebinar.restaurant.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.javawebinar.restaurant.web.absractController.AbstractRestaurantController;

@Controller
public class RootController extends AbstractRestaurantController {
    @GetMapping(value = "/")
    public String root() {
        return "redirect:restaurants";
    }

    @GetMapping(value = "/restaurants")
    public String restaurantList(Model model) {
        model.addAttribute("user", super.getUser(SecurityUtil.authu_id()));
        model.addAttribute("restaurants", super.getAllWithDishes(SecurityUtil.authu_id()));
        return "restaurants";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
}
