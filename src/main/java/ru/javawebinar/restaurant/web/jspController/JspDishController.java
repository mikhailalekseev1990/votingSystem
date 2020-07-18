package ru.javawebinar.restaurant.web.jspController;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.restaurant.model.Dish;
import ru.javawebinar.restaurant.web.absractController.AbstractDishController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping("/dish")
public class JspDishController extends AbstractDishController {

    @GetMapping("dish_delete")
    String delete(HttpServletRequest request) {
        super.delete(getDishId(request), getRestId(request));
        return "redirect:/restaurants";
    }

    @GetMapping("/dish_create")
    String create(Model model, HttpServletRequest request) {
        Dish dish = new Dish("", 0);
        request.setAttribute("restaurantId", getRestId(request));
        model.addAttribute("dish", dish);
        return "dishForm";
    }

    @GetMapping("/dish_update")
    String update(Model model, HttpServletRequest request) {
        Dish dish = get(getDishId(request), getRestId(request));
//        request.setAttribute("dishId", getDishId(request));
        request.setAttribute("restaurantId", getRestId(request));
        model.addAttribute("dish", dish);
        return "dishForm";
    }

    @PostMapping
    public String save(HttpServletRequest request) throws IOException {
        Dish dish = new Dish(request.getParameter("dish_name"), Integer.parseInt(request.getParameter("dish_price")));

        if (request.getParameter("dishId").isEmpty()) {
            super.create(dish, getRestId(request));
        } else {
            super.update(dish, getDishId(request), getRestId(request));
        }
        return "redirect:restaurants";
    }


    private int getDishId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("dishId"));
        return Integer.parseInt(paramId);
    }

    private int getRestId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("restId"));
        return Integer.parseInt(paramId);
    }
}
