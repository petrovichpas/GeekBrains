package com.geekbrains.geek.cloud.client;

import com.geekbrains.geek.cloud.common.TempFileWriter;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MainClient extends TempFileWriter {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8189)) {
            System.out.println("List of commands: \n" +
                                "/upload 1.txt \n" +
                                "/download 1.txt");

            String[] token = new Scanner(System.in).nextLine().split(" ", 2);

            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            output.writeUTF(token[0]);
            output.writeUTF(token[1]);

            if (token[0].equalsIgnoreCase("/upload")) {
                InputStream input = new FileInputStream(token[1]);
                writeFile(output, input);
            }
            else if (token[0].equalsIgnoreCase("/download")){
                OutputStream outputStream = new FileOutputStream("client_repository\\" + token[1]);
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());

                //32 java.net.SocketException: Connection reset
                // Наверное сервер закрывает соединение до клиента, отсюда и ошибка?
                // Как правильно удержать соединение на сервере пока клиент в сети?
                writeFile(outputStream, inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}