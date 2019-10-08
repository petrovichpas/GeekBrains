package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class MainServer {
    public Vector<ClientHandler> clients;

    public MainServer() {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;

        try {
            AuthService.connect();
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился!");
                new ClientHandler(this, socket);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        AuthService.disconnect();
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
    }

    public void deleteClient(ClientHandler client){
        clients.remove(client);
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler o: clients) {
            o.sendMsg(msg);
        }
    }

    void personalMsg(ClientHandler from, String to, String message) {
        for (ClientHandler cl : clients) {
            if (cl.getNick().equals(to)) {
                if (from.getNick().equals(to)) {
                    from.sendMsg("Talk to yourself is a bad idea. Spend time more interesting:" +
                            "\n" + "1) https://www.youtube.com/" +
                            "\n" + "2) https://rt.pornhub.com/");
                    return;
                }
                cl.sendMsg("Private message from " + from.getNick() + ": " + message);
                from.sendMsg("Private message to " + to + ": " + message);
                return;
            }
        }
        from.sendMsg("Client with nickname " + to + " not find");
    }

    boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nick)) {
                return true;
            }
        }
        return false;
    }
}
