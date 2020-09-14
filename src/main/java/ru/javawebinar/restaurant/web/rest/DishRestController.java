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
    public static final String REST_URL = "/rest/restaurants";

    @Override
    @GetMapping("/{r_id}/dishes/{d_id}")
    public Dish get(@PathVariable int d_id, @PathVariable int r_id) {
        return super.get(d_id, r_id);
    }

    @Override
    @DeleteMapping("/{r_id}/dishes/{d_id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int d_id, @PathVariable int r_id) {
        super.delete(d_id, r_id);
    }

    @Override
    @GetMapping("/{r_id}/dishes")
    public List<Dish> getAll(@PathVariable int r_id) {
        return super.getAll(r_id);
    }

    @Override
    @PutMapping(value = "/{r_id}/dishes/{d_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable int d_id, @PathVariable int r_id) {
        super.update(dish, d_id, r_id);
    }

    @PostMapping(value = "/{r_id}/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@RequestBody Dish dish, @PathVariable int r_id) {
        Dish created = super.create(dish, r_id);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/"+r_id+"/dishes/{d_id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
