package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.repo.ProductRepository;

@Controller
public class MainController {

    @RequestMapping("/shop")
    public String indexPage(Model model) {
        model.addAttribute("activePage", "None");
        return "index";
    }
}
