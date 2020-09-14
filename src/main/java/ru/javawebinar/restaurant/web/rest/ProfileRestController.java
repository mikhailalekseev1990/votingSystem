package ru.javawebinar.restaurant.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.restaurant.Utils.UserUtil;
import ru.javawebinar.restaurant.model.Dish;
import ru.javawebinar.restaurant.model.Role;
import ru.javawebinar.restaurant.model.User;
import ru.javawebinar.restaurant.to.UserTo;
import ru.javawebinar.restaurant.web.absractController.AbstractUserController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = ProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestController extends AbstractUserController {
    public static final String REST_URL = "rest/profile";

}
