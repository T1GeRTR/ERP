package com.mtv.erp.dao;

import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.User;

import java.util.List;

public interface UserDao {
    User insert (User user) throws ServerException;

    boolean delete (int id);

    boolean update (User user);

    User getById (int id);

    List<User> getAll ();

    List<User> getByDepartamentId(int id);

    List<User> getByPositionId(int id);

    User getHeadByDepartamentId(int id);
}
