package ru.geekbrains.map;

import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.models.Role;
import java.sql.*;


public class RoleMapper {
    private final Connection connection;

    @Autowired
    public RoleMapper(Connection connection) {
        this.connection = connection;
    }

    public Role findById(int id) throws SQLException{
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM roles WHERE id = " + id);
        try(ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt(id));
                role.setName(resultSet.getString("name"));
                return role;
            }
        }
        throw  new SQLException();
    }
}