package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.geekbrains.service.Policy;

@Controller
public class UserController {
    private final Policy policy;

    @Autowired
    public UserController(Policy policy) {
        this.policy = policy;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/deposit")
    public String deposit() {
        return "";
    }

    @GetMapping("/withdraw")
    public String withdraw() {
        return "";
    }

    @GetMapping("/balance")
    public String checkBalance() {
        return "";
    }

    @GetMapping("/pay")
    public String payBill() {
        return "";
    }
}
