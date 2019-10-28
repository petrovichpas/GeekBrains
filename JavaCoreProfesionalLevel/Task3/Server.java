package JavaCoreProfessionalLevel.Task3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket server = null;
        Socket client = null;
        ObjectInputStream in = null;
        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен. Ожидаем клиентов...");
            client = server.accept();
            System.out.println("Клиент подключился");
            in = new ObjectInputStream(client.getInputStream());
            Main mn = (Main) in.readObject();
            mn.read50Bytes();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                client.close();
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

