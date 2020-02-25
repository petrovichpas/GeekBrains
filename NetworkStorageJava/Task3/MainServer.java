package com.geekbrains.geek.cloud.server;

import com.geekbrains.geek.cloud.common.TempFileWriter;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer extends TempFileWriter {
    public static void main (String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8189);
        System.out.println("Сервер запущен. Ожидаем подключение клиента");
        Socket socket = serverSocket.accept();
        System.out.println("Клиент подключился");
        DataInputStream inputStream = new DataInputStream(socket.getInputStream());

        new Thread(()->{
            String command = null, fileName = null;

            while (true){
                try {
                    command = inputStream.readUTF();
                    fileName = inputStream.readUTF();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (command.equalsIgnoreCase("/upload")) {
                    try {
                        OutputStream outputStream = new FileOutputStream("server_repository\\" + fileName);
                        writeFile(outputStream, inputStream);
                        break; //временное решение while бросает искл. когда отключается клиент
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                else if (command.equalsIgnoreCase("/download")) {
                    try {
                        OutputStream output = new DataOutputStream(socket.getOutputStream());
                        InputStream input = new FileInputStream("server_repository\\" + fileName);
                        writeFile(output, input);
                        break; //временное решение while бросает искл. когда отключается клиент
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (command.equalsIgnoreCase("/close")) {
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
