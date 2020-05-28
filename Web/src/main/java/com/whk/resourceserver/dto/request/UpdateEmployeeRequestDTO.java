package com.whk.resourceserver.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UpdateEmployeeRequestDTO implements Serializable {
    private static final long serialVersionUID = 550653192670033757L;

    @ApiModelProperty(required = false,value = "Integer",example = "132131")
    private Integer employeeId;

    @ApiModelProperty(required = false,value = "String",example = "汪宏坤")
    private String name;

    @ApiModelProperty(required = false,value = "char",example = "男")
    private char sex;

    @ApiModelProperty(required = false,value = "Integer",example = "21")
    private Integer age;

    @ApiModelProperty(required = false,value = "String",example = "职位")
    private String position;

    @ApiModelProperty(required = false,value = "BigDecimal",example = "1500")
    private BigDecimal salary;

    @ApiModelProperty(required = false,value = "Date",example = "")
    private Date entryTime;

    @ApiModelProperty(required = false,value = "Date",example = "")
    private Date quitTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(Date quitTime) {
        this.quitTime = quitTime;
    }
}
