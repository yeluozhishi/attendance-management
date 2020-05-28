package com.whk.resourceserver.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class ManagerRequestDTO implements Serializable {
    private static final long serialVersionUID = -5539980989283668427L;

    @ApiModelProperty(required = true,value = "String",example = "manager")
    private String accountnumber;

    @ApiModelProperty(required = true,value = "String",example = "123")
    private String password;

    @ApiModelProperty(required = true,value = "String",example = "321")
    private String psw_new;

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

    public String getPsw_new() {
        return psw_new;
    }

    public void setPsw_new(String psw_new) {
        this.psw_new = psw_new;
    }
}
