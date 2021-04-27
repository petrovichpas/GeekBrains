package com.geekbrains.geek.cloud.client;

import com.geekbrains.geek.cloud.common.TempFileWriter;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MainClient extends TempFileWriter {
    public static void main(String[] args) {
        try (Socket client = new Socket("localhost", 8189)) {
            System.out.println("List of commands:\n" +
                    "/upload 1.txt\n" +
                    "/download 1.txt\n" +
                    "/close client");


            while (!client.isClosed()) {
                InputStream inputStream = new DataInputStream(client.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
                String[] clientRequest = new Scanner(System.in).nextLine().split(" ", 2);
                System.out.println(clientRequest[0]);
                outputStream.writeUTF(clientRequest[0]);
                System.out.println(clientRequest[0]);
                outputStream.writeUTF(clientRequest[1]);

                switch (clientRequest[0]){
                    case "/upload":
                        writeFile(new FileInputStream(clientRequest[1]), outputStream);
                        client.close();
                        System.out.println(1111);
                        break;
                    case "/download":
                        writeFile(inputStream, new FileOutputStream("client_repository\\" + clientRequest[1]));
                        break;
                    case "/close":
                        client.close();
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}