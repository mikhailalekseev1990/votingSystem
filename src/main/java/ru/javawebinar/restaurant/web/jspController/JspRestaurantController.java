package ru.javawebinar.restaurant.web.jspController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.restaurant.model.Restaurant;
import ru.javawebinar.restaurant.web.SecurityUtil;
import ru.javawebinar.restaurant.web.absractController.AbstractRestaurantController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/restaurants")
public class JspRestaurantController extends AbstractRestaurantController {

    @GetMapping("/delete")
    String delete(HttpServletRequest request) {
        super.delete(getRestaurantId(request));
        return "redirect:/restaurants";
    }

    @GetMapping("/create")
    String create(Model model) {
        Restaurant restaurant = new Restaurant("");
        model.addAttribute("restaurant", restaurant);
        return "restaurantForm";
    }

    @GetMapping("/update")
    String update(Model model, HttpServletRequest request) {
        Restaurant restaurant = super.get(getRestaurantId(request));
        model.addAttribute("restaurant", restaurant);
        return "restaurantForm";
    }

    @PostMapping
    public String save(HttpServletRequest request) {
        Restaurant restaurant = new Restaurant(request.getParameter("restaurant_name"));
        String r_id = request.getParameter("r_id");
        if (StringUtils.isEmpty(r_id)) {
            super.create(restaurant);
        } else {
            super.update(restaurant, getRestaurantId(request));
        }
        return "redirect:restaurants";
    }

    @GetMapping
    public String getAll(Model model) {
        List<Restaurant> restaurants = super.getAll();
        model.addAttribute("user", super.getUser(SecurityUtil.authu_id()));
        model.addAttribute("restaurants", restaurants);
        return "restaurants";
    }

    @GetMapping("/vote")
    public String vote(HttpServletRequest request){
        super.vote(getRestaurantId(request));
        return "redirect:/restaurants";
    }

    private int getRestaurantId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("r_id"));
        return Integer.parseInt(paramId);
    }

}
