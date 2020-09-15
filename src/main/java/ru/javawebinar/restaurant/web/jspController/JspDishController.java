package ru.javawebinar.restaurant.web.jspController;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.restaurant.model.Dish;
import ru.javawebinar.restaurant.web.absractController.AbstractDishController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping("/dish")
@PreAuthorize("hasRole('RESTAURANT_ADMIN')")
public class JspDishController extends AbstractDishController {

    @GetMapping("delete/{r_id}/{d_id}")
    String delete(HttpServletRequest request, @PathVariable int r_id, @PathVariable int d_id) {
        super.delete(d_id, r_id);
        return "redirect:/restaurants";
    }

    @GetMapping("/create/{r_id}")
    String create(Model model, HttpServletRequest request, @PathVariable int r_id) {
        Dish dish = new Dish("", 0);
        request.setAttribute("restaurantId", r_id);
        model.addAttribute("dish", dish);
        return "dishForm";
    }

    @GetMapping("/update/{r_id}/{d_id}")
    String update(Model model, HttpServletRequest request, @PathVariable int r_id, @PathVariable int d_id) {
        Dish dish = get(d_id, r_id);
        request.setAttribute("restaurantId", r_id);
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
