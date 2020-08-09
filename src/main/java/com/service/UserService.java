package com.service;

import com.dbc.InF.UserDBCInF;
import com.dbc.UserDBC;
import com.model.User;
import com.service.InF.UserInF;

import java.io.File;
import java.util.List;

public class UserService implements UserInF {
    UserDBCInF userDBC = new UserDBC();

    @Override
    public void insert(User user) {
        userDBC.insert(user);
    }

    @Override
    public void edit(User user) {
        User oldUser = userDBC.get(user.getId());

        oldUser.setEmail(user.getEmail());
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setRoleId(user.getRoleId());
        if (user.getAvatar() != null) {
            // XOA ANH CU DI
            String fileName = oldUser.getAvatar();
            String dir = "/upload";
            File file = new File(dir + "/" + fileName);
            if (file.exists()) {
                file.delete();
            }
            // THEM ANH MOI
            oldUser.setAvatar(user.getAvatar());
        }

        userDBC.edit(oldUser);
    }

    @Override
    public void delete(int id) {
        userDBC.delete(id);
    }

    @Override
    public User get(String username) {
        return userDBC.get(username);
    }

    @Override
    public User get(int id) {
        return userDBC.get(id);
    }

    @Override
    public User login(String username, String password) {
        User user = this.get(username);
        if ((user != null) && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public boolean register(String email, String password, String username) {
        if (userDBC.checkExistUsername(username)) {
            return false;
        }
        userDBC.insert(new User(email, username, password));
        return false;
    }

    @Override
    public List<User> getAll() {
        return userDBC.getAll();
    }

    @Override
    public List<User> search(String keyword) {
        return userDBC.search(keyword);
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDBC.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDBC.checkExistUsername(username);
    }
}
