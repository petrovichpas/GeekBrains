package ru.geekbrains.service;

import java.math.BigDecimal;

public interface Payment {
    void payBill(BigDecimal amount);
}
