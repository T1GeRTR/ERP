package com.mtv.erp.service;

import com.mtv.erp.dao.UserDao;
import com.mtv.erp.exception.ServerException;
import com.mtv.erp.model.User;
import com.mtv.erp.response.UserGetFromDate;
import com.mtv.erp.response.UserGetFromDateById;
import com.mtv.erp.response.UserGetAllDtoResponse;
import com.mtv.erp.response.planfixResponse.PlanfixUser;
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
public class UserService {

    @Autowired
    UserDao userDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(HoursService.class);

    public List<UserGetAllDtoResponse> update() throws ServerException {
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
        return getAll();
    }

    public List<UserGetAllDtoResponse> getAll() throws ServerException {
        List<UserGetAllDtoResponse> getAllDtoResponses = new ArrayList<>();
        for (User user : userDao.getAll()) {
            getAllDtoResponses.add(new UserGetAllDtoResponse(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail()));
        }
        return getAllDtoResponses;
    }

    public List<UserGetFromDate> getFromDate(String monthYear) throws ServerException {
        List<UserGetFromDate> getFromDates = new ArrayList<>();
        int month = MonthYearConverter.getMonth(monthYear);
        int year = MonthYearConverter.getYear(monthYear);
        LocalDate from = LocalDate.of(year, month, 1);
        LocalDate to = (from.getMonthValue() < LocalDate.now().getMonthValue() && from.getYear() <= LocalDate.now().getYear()) ? from.withDayOfMonth(from.lengthOfMonth()) : LocalDate.now();
        for (User user : userDao.getFromDate(from, to)) {
            getFromDates.add(new UserGetFromDate(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getHours()));
        }
        return getFromDates;
    }

    public UserGetFromDateById getFromDateById(int id, String monthYear) throws ServerException {
        int month = MonthYearConverter.getMonth(monthYear);
        int year = MonthYearConverter.getYear(monthYear);
        LocalDate from = LocalDate.of(year, month, 1);
        LocalDate to = (from.getMonthValue() < LocalDate.now().getMonthValue() && from.getYear() <= LocalDate.now().getYear()) ? from.withDayOfMonth(from.lengthOfMonth()) : LocalDate.now();
        User user = userDao.getFromDateById(from, to, id);
        return new UserGetFromDateById(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getHours());
    }
}
