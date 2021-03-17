package com.mtv.erp.mybatis.daoimpl;

import com.mtv.erp.dao.UserDao;
import com.mtv.erp.exception.ErrorCode;
import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.User;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserDaoImpl extends DaoImplBase implements UserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User insert(User user) throws ServerException {
        LOGGER.debug("DAO insert");
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).insert(user);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't insert user {}", user, e);
                sqlSession.rollback();
                throw new ServerException(ErrorCode.DATABASE_ERROR);
            }
            sqlSession.commit();
            user = getUserMapper(sqlSession).getById(user.getId());
        }
        return user;
    }

    @Override
    public boolean update(User user) throws ServerException {
        LOGGER.debug("DAO update");
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).update(user);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't update user {}", user, e);
                sqlSession.rollback();
                throw new ServerException(ErrorCode.CANT_UPDATE_USER);
            }
            sqlSession.commit();
        }
        return true;
    }

    @Override
    public boolean delete(int id) throws ServerException {
        LOGGER.debug("DAO delete");
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).delete(id);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't delete userId {}", id, e);
                sqlSession.rollback();
                throw new ServerException(ErrorCode.CANT_DELETE_USER, id);
            }
            sqlSession.commit();
        }
        return true;
    }


    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public List<User> getAll() throws ServerException {
        LOGGER.debug("DAO getAll");
        try (SqlSession sqlSession = getSession()) {
            try {
                return getUserMapper(sqlSession).getAll();
            } catch (RuntimeException e) {
                LOGGER.debug("Can't get all users", e);
                sqlSession.rollback();
                throw new ServerException(ErrorCode.DATABASE_ERROR);
            }
        }
    }

    @Override
    public List<User> insertAll(List<User> users) throws ServerException {
        LOGGER.debug("DAO insert all");
        try (SqlSession sqlSession = getSession()) {
            try {
                getUserMapper(sqlSession).insertAll(users);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't insert users {}", users, e);
                sqlSession.rollback();
                throw new ServerException(ErrorCode.DATABASE_ERROR);
            }
            sqlSession.commit();
        }
        return users;
    }

}
