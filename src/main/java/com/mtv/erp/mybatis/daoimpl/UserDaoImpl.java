package com.mtv.erp.mybatis.daoimpl;

import com.mtv.erp.dao.UserDao;
import com.mtv.erp.exception.ErrorCode;
import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.User;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
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
                if (e.getCause() instanceof MySQLIntegrityConstraintViolationException) {
                    throw new ServerException(ErrorCode.DUPLICATE_LOGIN, user.getLogin());
                }
                throw new ServerException(ErrorCode.DATABASE_ERROR);
            }
            sqlSession.commit();
            user = getUserMapper(sqlSession).getById(user.getId());
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public List<User> getByDepartamentId(int id) {
        return null;
    }

    @Override
    public List<User> getByPositionId(int id) {
        return null;
    }

    @Override
    public User getHeadByDepartamentId(int id) {
        return null;
    }
}
