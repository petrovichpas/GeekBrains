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

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            StringBuilder fileName = new StringBuilder();

            byte fileLength = (byte) dataInputStream.read();
            for (int i = 0; i < fileLength; i++) {
                fileName.append((char) dataInputStream.read());
            }

            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream("server_repository\\" + fileName));
            byte[] buf = new byte[8152];
            int tmp;
            while ((tmp = dataInputStream.read(buf)) != -1){
                outputStream.write(buf,0, tmp);
            }

            dataInputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
