package com.geekbrains.geek.cloud.client;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8189)) {
            File sendingFile = new File("Car.jpg");
            // не понял для чего нужен сигнальный байт????
            //socket.getOutputStream().write(15);
            socket.getOutputStream().write(sendingFile.getName().length());
            socket.getOutputStream().write(sendingFile.getName().getBytes());
            socket.getOutputStream().write(Files.readAllBytes(Paths.get(sendingFile.toURI())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}