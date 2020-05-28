package com.whk.site.domain.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class AccountRequestDTO implements Serializable {

    private static final long serialVersionUID = 6081993110592918735L;
    private String accountnumber;

    private String password;

    private String psw_new;

    private Integer employeeId;

    private Date createTime;

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
