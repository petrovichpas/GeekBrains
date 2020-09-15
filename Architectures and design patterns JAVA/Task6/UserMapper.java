package ru.geekbrains.map;

import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.models.User;
import java.sql.*;



public class UserMapper {
    private final Connection connection;

    @Autowired
    public UserMapper(Connection connection) {
        this.connection = connection;
    }

    public User findById(int id) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = " + id);
        try(ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(id));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setAccountNumber(resultSet.getString("accountNumber"));
                return user;
            }
        }
        throw  new SQLException();
    }
}