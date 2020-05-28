package com.whk.site.domain.dto;

import java.io.Serializable;

public class SignDiaryRequestDTO implements Serializable {

    private static final long serialVersionUID = 4549660146014342874L;

    private String accountnumber;

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }
}
