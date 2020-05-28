package com.whk.resourceserver.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class EntryEmployeeRequestDTO implements Serializable {

    private static final long serialVersionUID = 7794541543516644158L;

    @ApiModelProperty(required = true,value = "String",example = "汪宏坤")
    private String name;

    @ApiModelProperty(required = true,value = "char",example = "男")
    private char sex;

    @ApiModelProperty(required = true,value = "Integer",example = "21")
    private Integer age;

    @ApiModelProperty(required = true,value = "String",example = "职位")
    private String position;

    @ApiModelProperty(required = true,value = "BigDecimal",example = "1500")
    private BigDecimal salary;

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
}
