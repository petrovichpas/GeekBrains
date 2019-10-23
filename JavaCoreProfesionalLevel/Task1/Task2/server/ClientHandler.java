package Task2.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler {
    private Server server;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private String nick;

    List<String> blackList;

    public String getNick() {
        return nick;
    }

    public ClientHandler(Server server, Socket socket) {
        try {
            this.socket = socket;
            this.server = server;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.blackList = new ArrayList<>();
            new Thread(() -> {
                try {
                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/auth")) { // /auth login72 pass72
                            String[] tokens = str.split(" ");
                            String newNick = DataBaseService.getNickByLoginAndPass(tokens[1], tokens[2]);
                            if (newNick != null) {
                                if (!server.isNickBusy(newNick)) {
                                    sendMsg("/authok");
                                    nick = newNick;
                                    server.subscribe(this);
                                    break;
                                } else {
                                    sendMsg("Учетная запись уже используется");
                                }
                            } else {
                                sendMsg("Неверный логин/пароль");
                            }
                        }
                    }

                    blackList = DataBaseService.getBlackList(getNick());
                    DataBaseService.loadHistory().forEach(ls-> sendMsg(ls));

                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/")) {
                            if (str.equals("/end")) {
                                out.writeUTF("/serverclosed");
                                break;
                            }
                            if (str.startsWith("/w ")) {
                                String[] tokens = str.split(" ", 3);
                                if (!DataBaseService.getBlackList(DataBaseService.getId(tokens[1])).contains(DataBaseService.getId(getNick()))) {
                                    server.sendPersonalMsg(this, tokens[1], tokens[2]);
                                }
                                else sendMsg("Вы не можете писать этому клиенту");
                            }
                            if (str.startsWith("/blacklist ")) {
                                String[] tokens = str.split(" ");
                                DataBaseService.addUserInBlacklist(DataBaseService.getId(getNick()), DataBaseService.getId(tokens[1]));
                                sendMsg("Вы добавили пользователя " + tokens[1] + " в черный список");
                            }
                        } else {
                            DataBaseService.saveHistory(getNick(), str);
                            server.broadcastMsg(this, getNick() + ": " + str);
                        }
                        System.out.println("Client: " + str);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    server.unsubscribe(this);
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkBlackList(String nick) {
        return blackList.contains(nick);
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
