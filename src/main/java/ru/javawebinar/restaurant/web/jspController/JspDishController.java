package ru.javawebinar.restaurant.web.jspController;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.restaurant.model.Dish;
import ru.javawebinar.restaurant.model.Restaurant;
import ru.javawebinar.restaurant.web.SecurityUtil;
import ru.javawebinar.restaurant.web.absractController.AbstractDishController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/dish")
public class JspDishController extends AbstractDishController {

    @GetMapping("dish_delete")
    String delete(HttpServletRequest request) {
        super.delete(getDishId(request), getRestaurantId(request));
        return "redirect:/restaurants";
    }

    @GetMapping("/dish_create")
    String create(Model model, HttpServletRequest request) {
        Dish dish = new Dish("", 0);
        request.setAttribute("restaurantId", getRestaurantId(request));
        model.addAttribute("dish", dish);
        return "dishForm";
    }

    @GetMapping("/dish_update")
    String update(Model model, HttpServletRequest request) {
        Dish dish = get(getDishId(request), getRestaurantId(request));
//        request.setAttribute("d_id", getd_id(request));
        request.setAttribute("restaurantId", getRestaurantId(request));
        model.addAttribute("dish", dish);
        return "dishForm";
    }

    @PostMapping
    public String save(HttpServletRequest request) throws IOException {
        Dish dish = new Dish(request.getParameter("dish_name"), Integer.parseInt(request.getParameter("dish_price")));

        if (request.getParameter("d_id").isEmpty()) {
            super.create(dish, getRestaurantId(request));
        } else {
            super.update(dish, getDishId(request), getRestaurantId(request));
        }
        return "redirect:restaurants";
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
