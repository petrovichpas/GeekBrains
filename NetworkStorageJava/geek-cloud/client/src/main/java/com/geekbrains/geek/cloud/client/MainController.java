package com.geekbrains.geek.cloud.client;

import com.geekbrains.geek.cloud.common.*;
import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    Button deleteFromClient;

    @FXML
    ListView<String> clientListView, serverListView;

    @FXML
    HBox mainNode;

    @FXML
    VBox loginNode;

    @FXML
    TextField loginField;

    @FXML
    PasswordField passwordField;

    @FXML
    Label labelAuthorized, labelUserName;

    private static Socket socket;
    private static ObjectEncoderOutputStream out;
    private static ObjectDecoderInputStream in;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Thread thread = new Thread(() -> {

            try {
                socket = new Socket("localhost", 8189);
                out = new ObjectEncoderOutputStream(socket.getOutputStream());
                in = new ObjectDecoderInputStream(socket.getInputStream(), 300 * 1024 * 1024);
                while (true) {
                    AbstractMessage abstractMessage = (AbstractMessage) in.readObject();
                    if (abstractMessage instanceof AuthMessage) {
                        AuthMessage authMessage = (AuthMessage) abstractMessage;
                        if (authMessage.getMessage().equals("True")) {
                            //скрываем узел авторизации
                            loginNode.setVisible(false);
                            loginNode.setManaged(false);
                            //показываем основной узел
                            mainNode.setVisible(true);
                            mainNode.setManaged(true);
                            //устанавливаем заголовок для списка файлов пользователя
                            updateUI(() ->  labelUserName.setText(loginField.getText()));
                            break;
                        }
                        else if (authMessage.getMessage().equals("False")) {
                            Platform.runLater(() -> labelAuthorized.setText("Неверный логин или пароль"));
                        }
                        else if (authMessage.getMessage().equals("FalseLogin")) {
                            Platform.runLater(() -> labelAuthorized.setText("Логин уже занят"));
                        }
                    }
                }

                sendMessage(new UpdateServerMessage());

                while (true) {
                    AbstractMessage abstractMessage = (AbstractMessage) in.readObject();
                    if (abstractMessage instanceof FileMessage) {
                        FileMessage fileMessage = (FileMessage) abstractMessage;
                        Files.write(Paths.get("client/repository/" + fileMessage.getFileName()), fileMessage.getFile(), StandardOpenOption.CREATE); //StandardOpenOption.CREATE всегда оздаёт/перезаписывает новые объекты
                        updateFilesList();
                    }
                    if (abstractMessage instanceof UpdateServerMessage) {
                        UpdateServerMessage refreshServerMsg = (UpdateServerMessage) abstractMessage;
                        updateServerFilesList(refreshServerMsg.getServerFileList());
                    }
                }
            } catch (ClassNotFoundException | IOException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    out.close();
                    in.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        updateFilesList();
    }

    public void signIn() {
        sendMessage(new AuthMessage(loginField.getText(), passwordField.getText()));
    }

    public void join() {
        sendMessage(new RegistrationMessage(loginField.getText(), passwordField.getText()));
    }

    public void downloadFile(ActionEvent actionEvent) {
        sendMessage(new DownloadRequest(loginField.getText(), serverListView.getSelectionModel().getSelectedItem()));
    }

    public void uploadFile(ActionEvent actionEvent) {
        try {
            sendMessage(new FileMessage(Paths.get("client/repository/" + clientListView.getSelectionModel().getSelectedItem()), loginField.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(ActionEvent actionEvent) {
        if (clientListView.getSelectionModel().getSelectedItem() != null) {
            try {
                Files.delete(Paths.get("client/repository/" + clientListView.getSelectionModel().getSelectedItem()));
                updateFilesList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (serverListView.getSelectionModel().getSelectedItem() != null) {
            sendMessage(new DeleteRequest(loginField.getText(), serverListView.getSelectionModel().getSelectedItem()));
        }
    }

    private void updateFilesList() {
        updateUI(() -> {
            try {
                clientListView.getItems().clear();
                Files.list(Paths.get("client/repository")).map(p -> p.getFileName().toString()).forEach(o -> clientListView.getItems().add(o));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void updateServerFilesList(ArrayList<String> filesList) {
        updateUI(() -> {
            serverListView.getItems().clear();
            serverListView.getItems().addAll(filesList);
        });
    }

    private static void updateUI(Runnable runnable) {
        if (Platform.isFxApplicationThread()) {
            runnable.run();
        } else {
            Platform.runLater(runnable);
        }
    }

    static void sendMessage(AbstractMessage msg) {
        try {
            out.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
