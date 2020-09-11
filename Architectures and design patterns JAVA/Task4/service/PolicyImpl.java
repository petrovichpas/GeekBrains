package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;

@Service
@Transactional
public class PolicyImpl implements Policy {
    private UserService userService;
    private AdminService adminService;

    @Autowired
    public PolicyImpl(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @Override
    public String getLogs(String name) {
        return adminService.getLogs(name);
    }

    @Override
    public String getVideo() {
        return adminService.getVideo();
    }

    @Override
    public void restart() {
        adminService.restart();
    }

    @Override
    public void withdraw(BigDecimal amount) {
        userService.withdraw(amount);
    }

    @Override
    public void deposit(BigDecimal amount) {
        userService.deposit(amount);
    }
}