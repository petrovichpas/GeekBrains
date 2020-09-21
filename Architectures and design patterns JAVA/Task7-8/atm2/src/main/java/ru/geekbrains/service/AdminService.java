package ru.geekbrains.service;

public interface AdminService {
    String getLogs(String logName);
    String getVideo();
    void restart();
}
