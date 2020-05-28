package com.whk.resourceserver.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class UserLoginRequestDTO implements Serializable {

    private static final long serialVersionUID = 3194881164131699992L;

    @ApiModelProperty(required = true ,value = "String" , example = "wanghongkun")
    private String accountnumber;

    @ApiModelProperty(required = true ,value = "String" , example = "qwe")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }
}
