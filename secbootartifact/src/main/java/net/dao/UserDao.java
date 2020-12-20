package net.dao;

import net.model.Role;
import net.model.User;

import java.util.List;

public interface UserDao {
    User getUserByName(String name);

    void add(User user);

    void updateUsers(User user);

    void remove(long id);

    User getUserById(long id);

    List<User> listUsers();

    Role getRoleByName(String name);

    List<Role> listRoles();


}
