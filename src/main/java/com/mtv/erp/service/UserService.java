package com.mtv.erp.service;

import com.mtv.erp.dao.UserDao;
import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.User;
import com.mtv.erp.mybatis.daoimpl.UserDaoImpl;
import com.mtv.erp.utils.Planfix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public boolean update() throws ServerException {
        Planfix planfix = new Planfix();
        List<User> usersFromPf = planfix.getUsers();
        List<User> users = userDao.getAll();
        List<User> usersUpdate = new ArrayList<>();
        List<User> usersIdDelete = new ArrayList<>(users);
        List<User> usersNotAdd = new ArrayList<>();
        for (int i = 0; i < usersFromPf.size(); i++) {
            for (int j = 0; j < users.size(); j++) {
                if (usersFromPf.get(i).getId() == users.get(j).getId()) {
                    if (!usersFromPf.get(i).equals(users.get(j))) {
                        usersUpdate.add(usersFromPf.get(i));
                    }
                    usersNotAdd.add(usersFromPf.get(i));
                    break;
                }
            }
        }
        usersFromPf.removeAll(usersNotAdd);
        usersIdDelete.removeAll(usersNotAdd);
        usersIdDelete.removeAll(usersUpdate);
        if (usersFromPf.size() > 0) {
            userDao.insertAll(usersFromPf);
        }
        for (User user : usersUpdate) {
            userDao.update(user);
        }
        for (User user : usersIdDelete) {
            userDao.delete(user.getId());
        }
        return true;
    }

    public List<User> getAll() throws ServerException {
        return userDao.getAll();
    }
}
