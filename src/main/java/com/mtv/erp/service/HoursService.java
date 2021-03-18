package com.mtv.erp.service;

import com.mtv.erp.dao.HoursDao;
import com.mtv.erp.dao.UserDao;
import com.mtv.erp.exception.ErrorCode;
import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.LaborRecord;
import com.mtv.erp.model.User;
import com.mtv.erp.mybatis.daoimpl.HoursDaoImpl;
import com.mtv.erp.response.planfixResponse.PlanfixLaborRecord;
import com.mtv.erp.utils.MonthYearConverter;
import com.mtv.erp.utils.Planfix;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HoursService {

    @Autowired
    HoursDao hoursDao;

    @Autowired
    UserDao userDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(HoursService.class);

    public List<LaborRecord> update() throws ServerException {
        Planfix planfix = new Planfix();
        LocalDate now = LocalDate.now();
        LocalDate from;
        List<PlanfixLaborRecord> planfixLaborRecords;
        if (now.getDayOfMonth() <= 8) {
            from = LocalDate.of(now.getYear(), now.getMonthValue() - 1, 1);
        } else {
            from = now.withDayOfMonth(1);
        }
        planfixLaborRecords = planfix.getHours(from, now);
        LOGGER.debug("Hours in Planfix: {}", planfixLaborRecords.size());
        List<LaborRecord> laborRecords = hoursDao.getFromDate(from, now);
        List<LaborRecord> laborsUpdate = new ArrayList<>();
        List<LaborRecord> laborsIdDelete = new ArrayList<>(laborRecords);
        List<LaborRecord> laborsNotAdd = new ArrayList<>();
        List<LaborRecord> laborRecordsFromPlanfix = converter(planfixLaborRecords);
        List<Integer> deleteIds = new ArrayList<>();
        List<User> users = userDao.getAll();
        for (LaborRecord laborRecord : laborRecordsFromPlanfix) {
            for (User user : users) {
                if (laborRecord.getUser().getFirstname().equals(user.getFirstname()) && laborRecord.getUser().getLastname().equals(user.getLastname())) {
                    laborRecord.setUser(user);
                    break;
                }
            }
        }
        for (int i = 0; i < laborRecordsFromPlanfix.size(); i++) {
            for (int j = 0; j < laborRecordsFromPlanfix.size(); j++) {
                LaborRecord labor1 = laborRecordsFromPlanfix.get(i);
                LaborRecord labor2 = laborRecordsFromPlanfix.get(j);
                if (labor1.getUser().equals(labor2.getUser()) && labor1.getDate() == labor2.getDate() && labor1.getTaskId() == labor2.getTaskId() && labor1.getProjectId() == labor2.getProjectId())
                    if (i != j) {
                        if (!(deleteIds.contains(i) || deleteIds.contains(j))) {
                            labor1.setHours(labor1.getHours() + labor2.getHours());
                            deleteIds.add(j);
                        }
                    }
            }
        }
        for (int id : deleteIds) {
            laborRecordsFromPlanfix.remove(id);
        }
        for (int i = 0; i < laborRecordsFromPlanfix.size(); i++) {
            for (int j = 0; j < laborRecords.size(); j++) {
                boolean equals = laborRecordsFromPlanfix.get(i).getProjectId() == laborRecords.get(j).getProjectId() && laborRecordsFromPlanfix.get(i).getTaskId() == laborRecords.get(j).getTaskId() && laborRecordsFromPlanfix.get(i).getDate() == laborRecords.get(j).getDate() && laborRecordsFromPlanfix.get(i).getUser().getId() == laborRecords.get(j).getUser().getId();
                if (equals) {
                    if (!laborRecordsFromPlanfix.get(i).equals(laborRecords.get(j))) {
                        laborsUpdate.add(laborRecordsFromPlanfix.get(i));
                    }
                    laborsNotAdd.add(laborRecordsFromPlanfix.get(i));
                    break;
                }
            }
        }
        laborRecordsFromPlanfix.removeAll(laborsNotAdd);
        laborsIdDelete.removeAll(laborsNotAdd);
        laborsIdDelete.removeAll(laborsUpdate);
        LOGGER.debug("Hours add: {}", laborRecordsFromPlanfix.size());
        if (laborRecordsFromPlanfix.size() > 0) {
            hoursDao.insertAll(laborRecordsFromPlanfix);
        }
        LOGGER.debug("Hours update: {}", laborsUpdate.size());
        for (LaborRecord laborRecord : laborsUpdate) {
            hoursDao.update(laborRecord);
        }
        LOGGER.debug("Hours delete: {}", laborsIdDelete.size());
        for (LaborRecord laborRecord : laborsIdDelete) {
            hoursDao.delete(laborRecord.getId());
        }
        return hoursDao.getFromDate(now.withDayOfMonth(1), now);
    }

    private List<LaborRecord> converter(List<PlanfixLaborRecord> planfixLaborRecords) {
        List<LaborRecord> laborRecords = new ArrayList<>();
        for (PlanfixLaborRecord planfixLaborRecord : planfixLaborRecords) {
            planfixLaborRecord.setLocalDateTime();
            laborRecords.add(new LaborRecord(new User(planfixLaborRecord.getFirstname(), planfixLaborRecord.getLastname()), planfixLaborRecord.getStartTime().toLocalDate(), planfixLaborRecord.getLaborSpan().getHour(), (int) planfixLaborRecord.getTaskId(), planfixLaborRecord.getTaskTitle(), (int) planfixLaborRecord.getProjectId(), planfixLaborRecord.getProjectTitle()));
        }
        return laborRecords;
    }

    public List<LaborRecord> getFromDate(String monthYear) throws ServerException {
        int month = MonthYearConverter.getMonth(monthYear);
        int year = MonthYearConverter.getYear(monthYear);
        LocalDate from = LocalDate.of(year, month, 1);
        LocalDate to = (from.getMonthValue() < LocalDate.now().getMonthValue() && from.getYear() <= LocalDate.now().getYear()) ? from.withDayOfMonth(from.lengthOfMonth()) : LocalDate.now();
        return hoursDao.getFromDate(from, to);
    }


}
