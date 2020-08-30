package ru.javawebinar.restaurant.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.restaurant.model.Dish;
import ru.javawebinar.restaurant.web.absractController.AbstractDishController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController extends AbstractDishController {
    public static final String REST_URL = "/rest/restaurant";

    @Override
    @GetMapping("/{rId}/dishes/{id}")
    public Dish get(@PathVariable int id, @PathVariable int rId) {
        return super.get(id, rId);
    }

    @Override
    @DeleteMapping("/{rId}/dishes/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int rId) {
        super.delete(id, rId);
    }

    @Override
    @GetMapping("/{rId}/dishes")
    public List<Dish> getAll(@PathVariable int rId) {
        return super.getAll(rId);
    }

    @Override
    @PutMapping(value = "/{rId}/dishes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable int id, @PathVariable int rId) {
        super.update(dish, id, rId);
    }

    @PostMapping(value = "/{rId}/dishes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish, @PathVariable int rId) {
        Dish created = super.create(dish, rId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
