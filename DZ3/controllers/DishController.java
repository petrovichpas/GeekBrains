package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.Dish;
import ru.geekbrains.persist.DishRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:63342")
@RequestMapping("/api/v1/dish")
@RestController
public class DishController {
    private final DishRepository repository;

    @Autowired
    public DishController(DishRepository dishRepository) {
        this.repository = dishRepository;
        // временно
        dishRepository.save(new Dish("Пицца", BigDecimal.valueOf(450.99)));
        dishRepository.save(new Dish("Борщ", BigDecimal.valueOf(350.99)));
        dishRepository.save(new Dish("Пивасик", BigDecimal.valueOf(150.99)));
    }

    @PostMapping()
    public Dish create(@RequestBody Dish item) {
        return repository.save(item);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        repository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Optional<Dish> find(@PathVariable("id") long id){
        return repository.findById(id);
    }

    @GetMapping("/all")
    public List<Dish> findAll() {
        return repository.findAll();
    }
}
