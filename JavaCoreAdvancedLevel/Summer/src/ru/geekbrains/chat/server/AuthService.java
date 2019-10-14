package ru.geekbrains.chat.server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthService {
    private static Connection connection;
    private static Statement stmt;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:users.db");
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addUser(String login, String pass, String nick) {
        try {
            String query = "INSERT INTO main (login, password, nickname) VALUES (?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, login);
            ps.setInt(2, pass.hashCode());
            ps.setString(3, nick);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPass(String login, String pass) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT nickname, password FROM main WHERE login = '" + login + "'");
            int myHash = pass.hashCode();
            if (rs.next()) {
                String nick = rs.getString(1);
                int dbHash = rs.getInt(2);
                if (myHash == dbHash) {
                    return nick;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getId(String nick) {
        try {
            ResultSet resultSet = stmt.executeQuery("SELECT id FROM main WHERE nickname = '" + nick + "'");
            return resultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int addUserInBlacklist(String id_user, String id_block_user) {
        String sql = String.format("INSERT INTO blacklist (id_user, id_block_user) VALUES ('%s', '%s')", id_user, id_block_user);
        int resultSet = 0;
        try {
            resultSet = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public static List<String> getBlackList(String id) {
        List<String> blackList = new ArrayList<>();
        String sql = String.format("SELECT blacklist.id_block_user FROM main Inner JOIN blacklist ON blacklist.id_user = main.id WHERE main.id = '%s'", id);
        try {
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                blackList.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blackList;
    }
}