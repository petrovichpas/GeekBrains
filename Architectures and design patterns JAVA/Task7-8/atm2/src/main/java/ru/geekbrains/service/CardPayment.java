package ru.geekbrains.service;

import ru.geekbrains.models.User;
import java.math.BigDecimal;

public class CardPayment implements Payment {
    private User user;
    private BigDecimal amount;

    public CardPayment(User user, BigDecimal amount) {
        this.user = user;
        this.amount = amount;
    }

    @Override
    public void payBill(BigDecimal amount) {
        System.out.printf("Bill  paid from account: %s. Total: %s", user.getAccountNumber(), amount);
    }
}
