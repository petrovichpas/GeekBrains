package com.geekbrains.geek.cloud.server;

import com.geekbrains.geek.cloud.common.AuthMessage;
import com.geekbrains.geek.cloud.common.RegistrationMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.sql.*;

public class AuthHandler extends ChannelInboundHandlerAdapter {
    private boolean isAuthorized = false;
    private static Connection connection;
    private static Statement statement;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object message) throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:server/users.db");//подключение к базе данных
        statement = connection.createStatement();

            if (isAuthorized) {
                ctx.fireChannelRead(message);
                return;
            }

            if (message instanceof AuthMessage) {
                AuthMessage authMessage = (AuthMessage) message;
                //запрашиваем ИД пользователя по логину и паролю из БД, КНОПКА ВОЙТИ
                ResultSet resultSet = statement.executeQuery(String.format("SELECT id FROM users WHERE login = '%s' and password = '%s'", authMessage.getLogin(), authMessage.getPassword()));

                if (resultSet.next()) {
                    isAuthorized = true;
                    ctx.pipeline().addLast(new ServerHandler(authMessage.getLogin()));
                    ctx.writeAndFlush(new AuthMessage("True"));
                }
                else ctx.writeAndFlush(new AuthMessage("False"));
            }
            else if (message instanceof RegistrationMessage){
                RegistrationMessage registrationMessage = (RegistrationMessage) message;
                ResultSet resultSet = statement.executeQuery(String.format("SELECT id FROM users WHERE login = '%s'", registrationMessage.getLogin()));
                //выполняем запрос в БД если пользователь уже существует - сообщаем об этом, КНОПКА РЕГИСТРАЦИЯ
                if (resultSet.next()) {
                    ctx.writeAndFlush(new AuthMessage("FalseLogin"));
                }
                else { //иначе добавляем пользователя в БД и авторизовываем
                    statement.executeUpdate(String.format("INSERT INTO users (login, password) VALUES ('%s', '%s')",registrationMessage.getLogin(), registrationMessage.getPassword()));
                    isAuthorized = true;
                    ctx.pipeline().addLast(new ServerHandler(resultSet.getString("id")));
                    ctx.writeAndFlush(new AuthMessage("True"));
                }
            }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }
}
