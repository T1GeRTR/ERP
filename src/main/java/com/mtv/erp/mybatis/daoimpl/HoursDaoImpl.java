package com.mtv.erp.mybatis.daoimpl;

import com.mtv.erp.dao.HoursDao;
import com.mtv.erp.exception.ErrorCode;
import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.LaborRecord;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class HoursDaoImpl extends DaoImplBase implements HoursDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(HoursDaoImpl.class);

    @Override
    public List<LaborRecord> insertAll(List<LaborRecord> list) throws ServerException {
        LOGGER.debug("DAO insert all");
        try (SqlSession sqlSession = getSession()) {
            try {
                getHoursMapper(sqlSession).insertAll(list);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't insert hours {}", list, e);
                sqlSession.rollback();
                throw new ServerException(ErrorCode.DATABASE_ERROR);
            }
            sqlSession.commit();
        }
        return list;
    }

    @Override
    public List<LaborRecord> getFromDate(LocalDate from, LocalDate to) throws ServerException {
        LOGGER.debug("DAO get from date interval");
        try (SqlSession sqlSession = getSession()) {
            try {
                return getHoursMapper(sqlSession).getFromDate(from, to);
            } catch (RuntimeException e) {
                LOGGER.debug("Can't get hours from date {}, {}", from, to, e);
                throw new ServerException(ErrorCode.DATABASE_ERROR);
            }
        }
    }

    @Override
    public boolean delete(int id) throws ServerException {
        return false;
    }

    @Override
    public boolean update(LaborRecord laborRecord) throws ServerException {
//        LOGGER.debug("DAO update");
//        try (SqlSession sqlSession = getSession()) {
//            try {
//                getHoursMapper(sqlSession).update(laborRecord);
//            } catch (RuntimeException e) {
//                LOGGER.debug("Can't update user {}", laborRecord, e);
//                sqlSession.rollback();
//                throw new ServerException(ErrorCode.CANT_UPDATE_USER);
//            }
//            sqlSession.commit();
//        }
        return true;
    }
}
