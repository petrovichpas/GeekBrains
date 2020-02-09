package com.geekbrains.geek.cloud.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8189);
            System.out.println("Сервер запущен. Ожидаем подключение клиента");
            Socket socket = serverSocket.accept();
            System.out.println("Клиент подключился");

            BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
            StringBuilder fileName = new StringBuilder();

            int fileLength = in.read();
            for (int i = 0; i < fileLength; i++) {
                fileName.append((char) in.read());
            }

            OutputStream out = new FileOutputStream("server_repository\\" + fileName);

            int currentByte;
            while ((currentByte = in.read()) != -1){
                out.write(currentByte);
            }
            in.close();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
