package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.entity.Category;
import ru.geekbrains.exeptions.NotFoundException;
import ru.geekbrains.repo.CategoryRepository;
import ru.geekbrains.repo.ProductRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String categoryList(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("products", productRepository.findAll());
        return "categories";
    }

    @GetMapping("new")
    public String createCategory(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("products", productRepository.findAll());
        return "category";
    }

    @GetMapping("edit/{id}")
    public String updateCategory(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("category", categoryRepository.findById(id).orElseThrow(NotFoundException::new));
        model.addAttribute("products", productRepository.findAll());
        return "category";
    }

    @PostMapping
    public String saveCategory(@Valid Category category, BindingResult bindingResult) {
        categoryRepository.save(category);
        return "redirect:/category";
    }

    @DeleteMapping
    public String deleteCategory(@RequestParam("id") Long id) {
        categoryRepository.deleteById(id);
        return "redirect:/category";
    }
}
