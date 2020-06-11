package ru.geekbrains.springbootlesson.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springbootlesson.entity.Product;
import ru.geekbrains.springbootlesson.exeptions.NotFoundException;
import ru.geekbrains.springbootlesson.exeptions.ProductError;
import ru.geekbrains.springbootlesson.service.ProductService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductResource {
    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(path = "/{id}/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product findById(@PathVariable ("id") long id) {
        return productService.findById(id).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public void createProduct(@RequestBody Product product) {
        if (product.getId() != null) throw new IllegalArgumentException("Id already exists.");
        productService.save(product);
    }

    @PutMapping
    public void updateProduct(@RequestBody Product product) {
        productService.save(product);
    }

    @DeleteMapping(path = "/delete/{id}/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable("id") long id) {
        productService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<ProductError> notFoundExceptionHandler(NotFoundException exception) {
        return new ResponseEntity<>(new ProductError(HttpStatus.BAD_REQUEST.value(), "Object not found!", System.currentTimeMillis()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ProductError> illegalArgumentExceptionHandler(IllegalArgumentException exception) {
        return new ResponseEntity<>(new ProductError(HttpStatus.BAD_REQUEST.value(), exception.getMessage(),System.currentTimeMillis()), HttpStatus.BAD_REQUEST);
    }
}