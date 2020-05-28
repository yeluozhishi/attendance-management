package com.whk.resourceserver.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class DeleteEmployeeRequestDTO implements Serializable {

    private static final long serialVersionUID = 1712275647439120285L;

    @ApiModelProperty(required = true,value = "Integer",example = "132131")
    private Integer employeeId;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
