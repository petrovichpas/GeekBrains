package ru.geekbrains.service;

import java.math.BigDecimal;

public class UserServiceImpl implements UserService{
    @Override
    public void deposit(BigDecimal amount) {
        //пополняем счет
    }

    @Override
    public void withdraw(BigDecimal amount) {
        //снимаем  деньги
    }

    @Override
    public BigDecimal checkBalance() {
        //запрашиваем баланс
        return null;
    }

    @Override
    public String payBill() {
        return "Оплачиваем счет";
    }
}
