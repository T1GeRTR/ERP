package com.mtv.erp.service;

import com.mtv.erp.dao.UserDao;
import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.User;
import com.mtv.erp.response.planfixResponse.PlanfixUser;
import com.mtv.erp.utils.Planfix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(HoursService.class);

    public List<User> update() throws ServerException {
        Planfix planfix = new Planfix();
        List<User> usersFromPf = new ArrayList<>();
        List<PlanfixUser> usersFromPlanfix = planfix.getUsers();
        LOGGER.debug("Users in Planfix: {}", usersFromPlanfix.size());
        for (PlanfixUser user : usersFromPlanfix) {
            usersFromPf.add(new User(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail()));
        }
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
        LOGGER.debug("Users add: {}", usersFromPf.size());
        if (usersFromPf.size() > 0) {
            userDao.insertAll(usersFromPf);
        }
        LOGGER.debug("Users update: {}", usersUpdate.size());
        for (User user : usersUpdate) {
            userDao.update(user);
        }
        LOGGER.debug("Users delete: {}", usersIdDelete.size());
        for (User user : usersIdDelete) {
            userDao.delete(user.getId());
        }
        return userDao.getAll();
    }

    public List<User> getAll() throws ServerException {
        return userDao.getAll();
    }
}
