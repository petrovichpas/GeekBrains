package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.repo.ProductRepository;

@Controller
@RequestMapping("/shop")
public class MainController {
    ProductRepository productRepository;

    @Autowired
    public MainController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String productsPage(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "welcome";
    }

    @GetMapping("/product/{id}")
    public String productPage(Model model, @PathVariable("id") Long id) {
        model.addAttribute("productId", productRepository.findById(id).orElse(null));
        model.addAttribute("products", productRepository.findAll());
        return "product";
    }
}
