package ru.geekbrains.service;

import java.math.BigDecimal;

public class CashPayment implements Payment {
    private BigDecimal amount;

    public CashPayment(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public void payBill(BigDecimal amount) {
        System.out.println("Bill  paid in cash. Total: " + amount);
    }
}
