package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.geekbrains.service.Policy;
import java.math.BigDecimal;

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
    public String deposit(BigDecimal amount) {
        return "";
    }

    @GetMapping("/withdraw")
    public String withdraw(BigDecimal amount) {
        return "";
    }

    @GetMapping("/balance")
    public BigDecimal checkBalance() {
        return null;
    }

    @GetMapping("/pay")
    public String payBill() {
        return "";
    }
}
