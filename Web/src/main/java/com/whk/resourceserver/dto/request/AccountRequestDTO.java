package com.whk.resourceserver.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class AccountRequestDTO implements Serializable {

    private static final long serialVersionUID = -4711471263650931663L;

    @ApiModelProperty(required = false,value = "String",example = "wanghongkun")
    private String accountnumber;

    @ApiModelProperty(required = false,value = "String",example = "qwe")
    private String password;

    @ApiModelProperty(required = false,value = "String",example = "")
    private String psw_new;

    @ApiModelProperty(required = false,value = "Integer",example = "1")
    private Integer employeeId;

    @ApiModelProperty(required = false,value = "Date",example = "")
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
