package ru.javawebinar.restaurant.web.jspController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javawebinar.restaurant.model.Role;
import ru.javawebinar.restaurant.model.User;
import ru.javawebinar.restaurant.web.absractController.AbstractUserController;
import ru.javawebinar.restaurant.web.security.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/profile")
public class JspProfileController extends AbstractUserController {

    @GetMapping
    String create(Model model) {
        User user = new User("", "", "", Role.USER);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping
    public String save(HttpServletRequest request) throws IOException {
        String name = request.getParameter("user_name");
        String email = request.getParameter("user_email");
        String password = request.getParameter("user_password");

        Role role = "RESTAURANT_ADMIN".equals(request.getParameter("user_role")) ? Role.RESTAURANT_ADMIN : Role.USER;

        User user = new User(name, email, password, role);

        if (request.getParameter("u_id").isEmpty()) {
            super.create(user);
        } else {
            super.update(user, SecurityUtil.authUserId());
        }

        return "redirect:/login?message=registered&username=" + user.getEmail();
    }
}
