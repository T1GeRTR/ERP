package com.mtv.erp.utils;

import com.mtv.erp.model.LaborRecord;
import com.mtv.erp.response.GetUserLaborRecord;

import java.util.ArrayList;
import java.util.List;

public class LaborRecordConverter {

    public static List<GetUserLaborRecord> convertLaborRecord(List<LaborRecord> laborRecords) {
        List<GetUserLaborRecord> list = new ArrayList<>();
        for (LaborRecord laborRecord : laborRecords) {
            list.add(new GetUserLaborRecord(laborRecord.getId(), laborRecord.getUser(), laborRecord.getDate(), laborRecord.getHours(), String.valueOf(laborRecord.getTaskId()), laborRecord.getTaskTitle(), String.valueOf(laborRecord.getProjectId()), laborRecord.getProjectTitle()));
        }
        return list;
    }
}
