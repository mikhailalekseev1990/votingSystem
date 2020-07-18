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
        super.delete(getRestId(request));
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
        Restaurant restaurant = super.get(getRestId(request));
        model.addAttribute("restaurant", restaurant);
        return "restaurantForm";
    }

    @PostMapping
    public String save(HttpServletRequest request) {
        Restaurant restaurant = new Restaurant(request.getParameter("restaurant_name"));
        String restId = request.getParameter("restId");
        if (StringUtils.isEmpty(restId)) {
            super.create(restaurant);
        } else {
            super.update(restaurant, getRestId(request));
        }
        return "redirect:restaurants";
    }

    @GetMapping
    public String getAll(Model model) {
        List<Restaurant> restaurants = super.getAll();
        model.addAttribute("restaurants", restaurants);
        return "restaurants";
    }

    private int getRestId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("restId"));
        return Integer.parseInt(paramId);
    }
}
