package com.mtv.erp.dao;

import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.User1;

import java.util.List;

public interface UserDao {
    User1 insert (User1 user) throws ServerException;

    boolean delete (int id) throws ServerException;

    boolean update (User1 user) throws ServerException;

    User1 getById (int id);

    List<User1> getAll () throws ServerException;

    List<User1> insertAll(List<User1> users) throws ServerException;

    //List<User> getByDepartamentId(int id);

    //List<User> getByPositionId(int id);

    //User getHeadByDepartamentId(int id);


}
