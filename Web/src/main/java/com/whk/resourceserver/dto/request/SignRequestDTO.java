package com.whk.resourceserver.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class SignRequestDTO implements Serializable {
    private static final long serialVersionUID = -8297625500384772254L;

    @ApiModelProperty(required = true ,value = "String" , example = "wanghongkun")
    private String accountnumber;

    @ApiModelProperty(required = false ,value = "Date" , example = "")
    private Date startTime;

    @ApiModelProperty(required = false ,value = "Date" , example = "")
    private Date endTime;

    @ApiModelProperty(required = false ,value = "String" , example = "1：正常  2：异常")
    private String exception;

    @ApiModelProperty(required = true ,value = "int" , example = "1")
    private int pageNo;

    @ApiModelProperty(required = true ,value = "int" , example = "5")
    private int pageSize;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }
}
