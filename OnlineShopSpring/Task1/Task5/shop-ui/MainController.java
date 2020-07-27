package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.geekbrains.controllers.repr.ProductRepr;
import ru.geekbrains.service.ProductService;

@Controller
public class MainController {
    private final ProductService productService;

    @Autowired
    public MainController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String productsPage(Model model) {
        model.addAttribute("products", productService.findAllAndSplitProductsBy());
        return "products";
    }

    @GetMapping("/product/{id}")
    public String productPage(Model model, @PathVariable("id") Long id) {
        ProductRepr productRepr = productService.findById(id).orElseThrow(IllegalArgumentException::new);
        model.addAttribute("productId", productRepr);
        model.addAttribute("products", productService.findAllAndSplitProductsBy());
        return "product";
    }
}
