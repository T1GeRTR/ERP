package com.mtv.erp.requestPlanFix;

public class PfDtoRequest {
    private final String account = "Aseng";
    private final String status = "ACTIVE";
    private final int pageCurrent = 1;
    private final int pageSize = 5;
    private String method = "user.getList";

    public PfDtoRequest(String method) {
        this.method = method;
    }

    public String getAccount() {
        return account;
    }

    public String getStatus() {
        return status;
    }

    public int getPageCurrent() {
        return pageCurrent;
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getMethod() {
        return method;
    }
}
