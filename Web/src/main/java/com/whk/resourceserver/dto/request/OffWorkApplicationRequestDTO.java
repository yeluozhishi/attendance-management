package com.whk.resourceserver.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class OffWorkApplicationRequestDTO implements Serializable {
    private static final long serialVersionUID = 2397195225304858652L;

    @ApiModelProperty(required = true,value = "Integer",example = "132131")
    private Integer employeeId;

    @ApiModelProperty(required = true,value = "String",example = "病假、事假、")
    private String applicationType;

    @ApiModelProperty(required = true,value = "String",example = "")
    private String applicationReason;

    @ApiModelProperty(required = true,value = "Date",example = "")
    private Date startTime;

    @ApiModelProperty(required = true,value = "Date",example = "")
    private Date endTime;

    @ApiModelProperty(required = false,value = "Integer",example = "1")
    private Integer id;

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

    public String getApplicationReason() {
        return applicationReason;
    }

    public void setApplicationReason(String applicationReason) {
        this.applicationReason = applicationReason;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
