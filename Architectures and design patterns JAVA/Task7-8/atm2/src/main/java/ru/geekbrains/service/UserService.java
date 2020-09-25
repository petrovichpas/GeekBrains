package ru.geekbrains.service;

import java.math.BigDecimal;

public interface UserService {
    void deposit(BigDecimal amount);
    void withdraw(BigDecimal amount);
    BigDecimal checkBalance();
    String payBill();
}