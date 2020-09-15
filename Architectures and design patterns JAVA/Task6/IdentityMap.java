package ru.geekbrains.map;

import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.models.Role;
import ru.geekbrains.models.User;
import java.sql.SQLException;
import java.util.Map;

public class IdentityMap {
    private Map<Integer, User> userMap;
    private Map<Integer, Role> roleMap;
    private UserMapper userMapper;
    private RoleMapper roleMapper;

    @Autowired
    public IdentityMap(Map<Integer, User> userMap, UserMapper userMapper) {
        this.userMap = userMap;
        this.userMapper = userMapper;
    }

    public void addUser(Integer id) throws SQLException {
        userMap.put(userMapper.findById(id).getId(), userMapper.findById(id));
    }

    public User getUser(Integer id) throws SQLException {
        return userMapper.findById(id);
    }

    public void addRole(Integer id) throws SQLException {
        roleMap.put(roleMapper.findById(id).getId(), roleMapper.findById(id));
    }

    public Role getRole(Integer id) throws SQLException {
        return roleMapper.findById(id);
    }

}