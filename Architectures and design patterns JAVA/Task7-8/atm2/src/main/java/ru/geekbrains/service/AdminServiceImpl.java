package ru.geekbrains.service;

public class AdminServiceImpl implements AdminService {
    @Override
    public String getLogs(String logName) {
        return "Получаем логи";
    }

    @Override
    public String getVideo() {
        return "Получаем доступ к видеокамере";
    }

    @Override
    public void restart() {
        //Перезагрузка
    }
}
