package com.whk.site.domain.dto;

import java.io.Serializable;

public class LoginUserRequestDTO implements Serializable {

    private static final long serialVersionUID = 3284491615575829626L;

    private String accountnumber;

    private String password;

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
