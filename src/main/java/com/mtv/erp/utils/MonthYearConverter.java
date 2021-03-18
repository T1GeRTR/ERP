package com.mtv.erp.utils;

import com.mtv.erp.exception.ErrorCode;
import com.mtv.erp.exception.ServerException;

import java.time.LocalDate;

public class MonthYearConverter {

    public static int getMonth(String monthYear) throws ServerException {
        int month;
        if (monthYear.matches("-")) {
            month = Integer.parseInt(splitString(monthYear)[0]);
        } else {
            month = Integer.parseInt(monthYear);
        }
        if (month > 12) {
            throw new ServerException(ErrorCode.INVALID_MONTH);
        }
        return month;
    }

    public static int getYear(String monthYear) throws ServerException {
        int year;
        if (monthYear.matches("-")) {
            year = Integer.parseInt(splitString(monthYear)[1]);
        } else {
            year = LocalDate.now().getYear();
        }
        if (year > LocalDate.now().getYear()) {
            throw new ServerException(ErrorCode.INVALID_YEAR);
        }
        return year;
    }

    private static String[] splitString(String monthYear) throws ServerException {
        String[] array = monthYear.split("-");
        if (array.length < 2) {
            throw new ServerException(ErrorCode.INVALID_MONTH_YEAR);
        }
        return array;
    }
}
