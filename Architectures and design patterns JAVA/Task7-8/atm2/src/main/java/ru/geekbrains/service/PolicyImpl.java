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
    public String getLogs(String logName) {
        return null;
    }

    @Override
    public String getVideo() {
        return null;
    }

    @Override
    public void restart() {

    }

    @Override
    public void deposit(BigDecimal amount) {

    }

    @Override
    public void withdraw(BigDecimal amount) {

    }

    @Override
    public BigDecimal checkBalance() {
        return null;
    }

    @Override
    public String payBill() {
        return null;
    }
}