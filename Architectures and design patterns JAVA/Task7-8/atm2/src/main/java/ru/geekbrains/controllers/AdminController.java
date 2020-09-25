package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.geekbrains.service.Policy;

@Controller
public class AdminController {
    private final Policy policy;

    @Autowired
    public AdminController(Policy policy) {
        this.policy = policy;
    }

    @GetMapping("/logs")
    public String getLogs() {
        return "";
    }

    @GetMapping("/video")
    public String getVideo() {
        return "";
    }

    @GetMapping("/restart")
    public void restart() {

    }
}
