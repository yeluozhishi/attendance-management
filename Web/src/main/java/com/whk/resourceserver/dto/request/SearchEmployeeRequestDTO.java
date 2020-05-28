package com.whk.resourceserver.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SearchEmployeeRequestDTO implements Serializable {
    private static final long serialVersionUID = 4887252680703550352L;

    @ApiModelProperty(required = false,value = "Integer",example = "132131")
    private Integer employeeId;

    @ApiModelProperty(required = false,value = "String",example = "")
    private String condition;

    @ApiModelProperty(required = false,value = "String",example = "汪宏坤")
    private String name;

    @ApiModelProperty(required = false,value = "Integer",example = "1")
    private Integer ageStart;

    @ApiModelProperty(required = false,value = "Integer",example = "21")
    private Integer ageEnd;

    @ApiModelProperty(required = false,value = "Date",example = "")
    private Date entryTimeStart;

    @ApiModelProperty(required = false,value = "Date",example = "")
    private Date entryTimeEnd;

    @ApiModelProperty(required = false,value = "Date",example = "")
    private Date quitTimeStart;

    @ApiModelProperty(required = false,value = "Date",example = "")
    private Date quitTimeEnd;

    @ApiModelProperty(required = false,value = "String",example = "")
    private String position;

    @ApiModelProperty(required = false,value = "BigDecimal",example = "1500")
    private BigDecimal salaryStart;

    @ApiModelProperty(required = false,value = "BigDecimal",example = "1500")
    private BigDecimal salaryEnd;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAgeStart() {
        return ageStart;
    }

    public void setAgeStart(Integer ageStart) {
        this.ageStart = ageStart;
    }

    public Integer getAgeEnd() {
        return ageEnd;
    }

    public void setAgeEnd(Integer ageEnd) {
        this.ageEnd = ageEnd;
    }

    public Date getEntryTimeStart() {
        return entryTimeStart;
    }

    public void setEntryTimeStart(Date entryTimeStart) {
        this.entryTimeStart = entryTimeStart;
    }

    public Date getEntryTimeEnd() {
        return entryTimeEnd;
    }

    public void setEntryTimeEnd(Date entryTimeEnd) {
        this.entryTimeEnd = entryTimeEnd;
    }

    public Date getQuitTimeStart() {
        return quitTimeStart;
    }

    public void setQuitTimeStart(Date quitTimeStart) {
        this.quitTimeStart = quitTimeStart;
    }

    public Date getQuitTimeEnd() {
        return quitTimeEnd;
    }

    public void setQuitTimeEnd(Date quitTimeEnd) {
        this.quitTimeEnd = quitTimeEnd;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getSalaryStart() {
        return salaryStart;
    }

    public void setSalaryStart(BigDecimal salaryStart) {
        this.salaryStart = salaryStart;
    }

    public BigDecimal getSalaryEnd() {
        return salaryEnd;
    }

    public void setSalaryEnd(BigDecimal salaryEnd) {
        this.salaryEnd = salaryEnd;
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
