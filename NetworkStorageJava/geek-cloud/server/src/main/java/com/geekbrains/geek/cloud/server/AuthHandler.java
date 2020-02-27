package com.geekbrains.geek.cloud.server;

import com.geekbrains.geek.cloud.common.AuthMessage;
import com.geekbrains.geek.cloud.common.RegistrationMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class AuthHandler extends ChannelInboundHandlerAdapter {
    private boolean isAuthorized = false;
    private Connection connection;
    private Statement statement;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object message) throws Exception {
//        user1 11
//        user2 22
//        user3 33
//        user4 44
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:server/users.db"); //подключение к базе данных
        statement = connection.createStatement();

            if (isAuthorized) {
                ctx.fireChannelRead(message);
                return;
            }

            if (message instanceof AuthMessage) {
                AuthMessage authMessage = (AuthMessage) message;
                //запрашиваем ИД пользователя по логину и паролю из БД, КНОПКА ВОЙТИ
                ResultSet resultSet = statement.executeQuery(String.format("SELECT id FROM users WHERE login = '%s' and password = '%s'", authMessage.getLogin(), authMessage.getPassword()));
                //если пользователь найден, авторизовываем
                if (resultSet.next()) {
                    isAuthorized = true;
                    ctx.pipeline().addLast(new ServerHandler(authMessage.getLogin()));
                    ctx.writeAndFlush(new AuthMessage("True"));
                }
                else ctx.writeAndFlush(new AuthMessage("False"));
            }
            else if (message instanceof RegistrationMessage){
                RegistrationMessage regMessage = (RegistrationMessage) message;
                ResultSet resultSet = statement.executeQuery(String.format("SELECT id FROM users WHERE login = '%s'", regMessage.getLogin()));
                //выполняем запрос в БД если пользователь уже существует - сообщаем об этом, КНОПКА РЕГИСТРАЦИЯ
                if (resultSet.next()) {
                    ctx.writeAndFlush(new AuthMessage("FalseLogin"));
                }
                else { //иначе добавляем пользователя в БД и авторизовываем
                    statement.executeUpdate(String.format("INSERT INTO users (login, password) VALUES ('%s', '%s')",regMessage.getLogin(), regMessage.getPassword()));
                    isAuthorized = true;
                    Files.createDirectories(Paths.get("server/root_repository/" + regMessage.getLogin() + "/")); //создаем папку пользователя на сервере
                    ctx.pipeline().addLast(new ServerHandler(regMessage.getLogin()));
                    ctx.writeAndFlush(new AuthMessage("True"));
                }
            }
            connection.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
