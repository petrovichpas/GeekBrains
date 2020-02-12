package com.geekbrains.geek.cloud.client;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class MainClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8189)) {
            File sendingFile = new File("1.txt");
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            InputStream inputStream = new FileInputStream(sendingFile);

            //dataOutputStream.write(15);
            dataOutputStream.write(sendingFile.getName().length());
            dataOutputStream.write(sendingFile.getName().getBytes());

            // 1.5Гб = 47 секунд
            byte[] buf = new byte[8152];
            int tmp;
            while ((tmp = inputStream.read(buf)) != -1){
                dataOutputStream.write(buf, 0, tmp);
            }

            inputStream.close();
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}