package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.entity.Product;
import ru.geekbrains.service.ProductFilter;
import ru.geekbrains.service.ProductService;

import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/add")
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product";
    }

    @GetMapping("/update/{id}")
    public String updateProduct(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("product", productService.findById(id));
        return "product";
    }

    @PostMapping
    public String saveProduct(Product product) {
        productService.save(product);
        return "redirect:/product";
    }


    @GetMapping
    public String allProductsBetween(Model model, @RequestParam Map<String, String> params) {
        int pageIndex = 0;
        if (params.containsKey("pageIndex") && params.get("pageIndex") != null) {
            pageIndex = Integer.parseInt(params.get("pageIndex")) - 1;
        }
        Pageable pageRequest = PageRequest.of(pageIndex, 2);
        ProductFilter productFilter = new ProductFilter(params);

        Page<Product> page = productService.findAllByPagingAndFiltering(productFilter.getSpecification(), pageRequest);
        model.addAttribute("filter", productFilter.getFilter());
        model.addAttribute("page", page);
        return "products";
    }
}