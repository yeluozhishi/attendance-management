package com.whk.resourceserver.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class SearchOffWorkApplicationRequestDTO implements Serializable {
    private static final long serialVersionUID = 4724753702783564954L;

    @ApiModelProperty(required = true,value = "Integer",example = "132131")
    private Integer employeeId;

    @ApiModelProperty(required = false,value = "String",example = "病假 事假 ")
    private String applicationType;

    @ApiModelProperty(required = false ,value = "Date" , example = "")
    private Date startTime;

    @ApiModelProperty(required = false ,value = "Date" , example = "")
    private Date endTime;

    @ApiModelProperty(required = true ,value = "int" , example = "1")
    private int pageNo;

    @ApiModelProperty(required = true ,value = "int" , example = "5")
    private int pageSize;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

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
}
