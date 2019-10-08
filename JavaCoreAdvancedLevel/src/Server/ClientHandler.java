package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHandler {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private MainServer server;
    private String nick;

    public ClientHandler(MainServer server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();
                            if (str.startsWith("/auth")) {
                                // проверка на наличие пустых полей формы входа
                                String[] tokens = str.split(" ", 3);
                                String currentNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);

                                if (currentNick != null) {
                                    if (!server.isNickBusy(currentNick)) {
                                        sendMsg("/authok");
                                        nick = currentNick;
                                        server.subscribe(ClientHandler.this);
                                        break;
                                    } else sendMsg("Client already online");
                                }
                                else sendMsg("Wrong login/password");
                            }
                        }

                        while (true) {
                            String str = in.readUTF();
                            if (str.equalsIgnoreCase("/end")){
                                sendMsg("/clientExit");
                                break;
                            }
                            if (str.startsWith("/w")) {
                                String[] tokens = str.split(" ", 3);
                                try {
                                    server.personalMsg(ClientHandler.this, tokens[1], tokens[2]);
                                } catch (ArrayIndexOutOfBoundsException e){
                                    sendMsg("the message should be: /w 'nickname' 'message'");
                                }
                            }
                            else server.broadcastMsg(nick + ": " + str);
                        }
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    finally {
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    server.deleteClient(ClientHandler.this);
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   public String getNick() {
        return nick;
    }
}
