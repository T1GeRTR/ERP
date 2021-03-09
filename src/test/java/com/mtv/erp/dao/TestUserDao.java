package com.mtv.erp.dao;

import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.Department;
import com.mtv.erp.model.Position;
import com.mtv.erp.model.User;
import com.mtv.erp.model.UserType;
import com.mtv.erp.mybatis.daoimpl.UserDaoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TestUserDao {
    private final UserDao userDao = new UserDaoImpl();

    @Test
    public void testInsert() throws ServerException {
        User user = userDao.insert(new User("Misha", "passwordMisha", "Михаил", "Баранцев", "Сергеевич", "number", LocalDate.of(1990, 1, 1), new Department(1), new Position(1), UserType.EMPLOYEE));
        Assertions.assertNotEquals(0, user.getId());
    }
}
