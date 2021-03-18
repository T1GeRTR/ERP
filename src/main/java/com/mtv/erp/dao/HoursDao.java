package com.mtv.erp.dao;

import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.LaborRecord;

import java.time.LocalDate;
import java.util.List;

public interface HoursDao {
    List<LaborRecord> insertAll(List<LaborRecord> list) throws ServerException;

    List<LaborRecord> getFromDate(LocalDate from, LocalDate to) throws ServerException;

    boolean delete(int id) throws ServerException;

    boolean update(LaborRecord laborRecord) throws ServerException;
}
