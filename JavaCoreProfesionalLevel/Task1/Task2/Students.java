package Task2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Students {
    private static Connection connection;
    private static Statement stmt;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:students.db");
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createTableStudents(){
        try {
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS students (nick TEXT NOT NULL, fullName TEXT NOT NULL)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getNameByNick(String nick){
        try {
            stmt.executeQuery("SELECT * FROM students WHERE nick = '" + nick + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changeNickByName(String nick, String name){
        try {
            String sql = String.format("UPDATE students SET nick = '%s' WHERE name = '%s'", nick, name);
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTableStudents(){
        try {
            stmt.execute("DROP TABLE IF EXISTS students");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
