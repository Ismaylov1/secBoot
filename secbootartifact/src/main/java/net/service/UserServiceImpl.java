package net.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.dao.UserDao;
import net.model.User;
import net.model.Role;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    @Transactional
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    @Transactional
    public void add(User user) {
//        user.setPassword(NoOpPasswordEncoder.getInstance().encode(user.getPassword()));
        userDao.add(user);
    }

    @Override
    @Transactional
    public void updateUsers(User user) {
        userDao.updateUsers(user);
    }

    @Override
    @Transactional
    public void remove(long id) {
        userDao.remove(id);
    }

    @Override
    @Transactional
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    @Transactional
    public Role getRoleByName(String name) {
        return userDao.getRoleByName(name);
    }

    @Override
    public List<Role> listRoles() {
        return userDao.listRoles();
    }

}

