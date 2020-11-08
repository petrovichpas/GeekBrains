package geek.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import geek.persist.Dish;
import geek.persist.DishRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:63342")
@RequestMapping("/api/v1/dish")
@RestController
public class DishController {
    private final DishRepository repository;

    @Autowired
    public DishController(DishRepository repository) {
        this.repository = repository;
        // временно
        repository.save(new Dish("Пицца", BigDecimal.valueOf(450.99), "https://klike.net/uploads/posts/2019-06/1559545617_2.jpg"));
        repository.save(new Dish("Борщ", BigDecimal.valueOf(350.99), "https://klike.net/uploads/posts/2019-06/1559545617_2.jpg"));
        repository.save(new Dish("Пивасик", BigDecimal.valueOf(150.99), "https://klike.net/uploads/posts/2019-06/1559545617_2.jpg"));
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
