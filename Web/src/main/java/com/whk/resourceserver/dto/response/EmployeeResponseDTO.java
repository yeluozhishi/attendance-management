package com.whk.resourceserver.dto.response;

import com.whk.resourceserver.dto.common.ResultDTO;
import com.whk.resourceserver.entity.employeeEntity;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class EmployeeResponseDTO extends ResultDTO {

    @ApiModelProperty(value = "employeeEntity",example = "" )
    private employeeEntity employee;

    public employeeEntity getEmployee() {
        return employee;
    }

    public void setEmployee(employeeEntity employee) {
        this.employee = employee;
    }
}
