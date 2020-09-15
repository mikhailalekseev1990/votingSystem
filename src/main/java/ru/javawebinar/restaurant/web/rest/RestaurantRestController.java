package ru.javawebinar.restaurant.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.restaurant.model.Restaurant;
import ru.javawebinar.restaurant.web.absractController.AbstractRestaurantController;
import ru.javawebinar.restaurant.web.security.SecurityUtil;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantRestController extends AbstractRestaurantController {
   public static final String REST_URL = "/rest/restaurants";

    @GetMapping
    public List<Restaurant> getAll() {
        return super.getAll(SecurityUtil.authUserId());
    }

    @Override
    @PreAuthorize("hasRole('RESTAURANT_ADMIN')")
    @GetMapping("/{r_id}")
    public Restaurant get(@PathVariable int r_id) {
        return super.get(r_id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation( @RequestBody Restaurant restaurant) {
        Restaurant created = super.create(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{r_id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PreAuthorize("hasRole('RESTAURANT_ADMIN')")
    @DeleteMapping("/{r_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int r_id) {
        super.delete(r_id);
    }

    @Override
    @PreAuthorize("hasRole('RESTAURANT_ADMIN')")
    @PutMapping(value = "/{r_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant, @PathVariable int r_id) {
        super.update(restaurant, r_id);
    }


    @PreAuthorize("hasRole('RESTAURANT_ADMIN')")
    @GetMapping("/{r_id}/with-dishes")
    public Restaurant getWithDishes(@PathVariable int r_id) {
        return super.getWithDishes(r_id);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{r_id}/vote")
    public void vote( @PathVariable int r_id){
        super.vote(r_id);
    }
}
