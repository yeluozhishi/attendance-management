package com.whk.site.domain.dto;

import java.io.Serializable;

public class Mission_getRequestDTO implements Serializable {

    private static final long serialVersionUID = 9203024547585554689L;

    private String accountnumber_ee;

    private int pageSize;

    private int pageNo;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String getAccountnumber_ee() {
        return accountnumber_ee;
    }

    public void setAccountnumber_ee(String accountnumber_ee) {
        this.accountnumber_ee = accountnumber_ee;
    }
}
