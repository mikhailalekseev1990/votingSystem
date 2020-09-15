package ru.javawebinar.restaurant.web.jspController;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.restaurant.model.Restaurant;
import ru.javawebinar.restaurant.web.absractController.AbstractRestaurantController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@RequestMapping("/restaurants")
public class JspRestaurantController extends AbstractRestaurantController {

    @PreAuthorize("hasRole('RESTAURANT_ADMIN')")
    @GetMapping("/delete/{r_id}")
    String delete(HttpServletRequest request, @PathVariable int r_id) {
        super.delete(r_id);
        return "redirect:/restaurants";
    }

    @PreAuthorize("hasRole('RESTAURANT_ADMIN')")
    @GetMapping("/create")
    String create(Model model) {
        Restaurant restaurant = new Restaurant("");
        model.addAttribute("restaurant", restaurant);
        return "restaurantForm";
    }

    @PreAuthorize("hasRole('RESTAURANT_ADMIN')")
    @GetMapping("/update/{r_id}")
    String update(Model model, HttpServletRequest request,@PathVariable int r_id ) {
        Restaurant restaurant = super.get(r_id);
        model.addAttribute("restaurant", restaurant);
        return "restaurantForm";
    }

    @PreAuthorize("hasRole('RESTAURANT_ADMIN')")
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

    @PreAuthorize("hasRole('USER')")
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
