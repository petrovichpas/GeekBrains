package ru.geekbrains.models;

import ru.geekbrains.service.Payment;

import java.math.BigDecimal;

public class Bill {
    private BigDecimal amount;

    public Bill(BigDecimal amount) {
        this.amount = amount;
    }

    public void pay(Payment strategy){
        strategy.payBill(amount);
    }
}
