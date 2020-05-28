package com.whk.resourceserver.dto.request;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class CheckOffWorkApplicationRequestDTO implements Serializable {

    private static final long serialVersionUID = -5282458814248078722L;

    @ApiModelProperty(required = true,value = "Integer",example = "1")
    private Integer id;

    @ApiModelProperty(required = true,value = "String",example = "1:未审批、2:审批通过、3:审批未通过")
    private String approvalState;

    @ApiModelProperty(required = false,value = "String",example = "")
    private String approvalResult;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApprovalState() {
        return approvalState;
    }

    public void setApprovalState(String approvalState) {
        this.approvalState = approvalState;
    }

    public String getApprovalResult() {
        return approvalResult;
    }

    public void setApprovalResult(String approvalResult) {
        this.approvalResult = approvalResult;
    }
}
