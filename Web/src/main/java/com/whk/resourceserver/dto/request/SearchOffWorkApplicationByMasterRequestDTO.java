package com.whk.resourceserver.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class SearchOffWorkApplicationByMasterRequestDTO implements Serializable {
    private static final long serialVersionUID = 3387271513988025647L;

    @ApiModelProperty(required = false,value = "String",example = "")
    private Integer employeeId;

    @ApiModelProperty(required = false,value = "String",example = "病假 事假 带薪假 补签")
    private String applicationType;

    @ApiModelProperty(required = false,value = "String",example = "")
    private String condition;

    @ApiModelProperty(required = false ,value = "Date" , example = "")
    private Date startCreatTime;

    @ApiModelProperty(required = false ,value = "Date" , example = "")
    private Date endCreaateTime;

    @ApiModelProperty(required = false ,value = "String" , example = "1:未审批、2:审批通过、3:审批未通过")
    private String approvalState;

    @ApiModelProperty(required = true ,value = "int" , example = "1")
    private int pageNo;

    @ApiModelProperty(required = true ,value = "int" , example = "5")
    private int pageSize;

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getApprovalState() {
        return approvalState;
    }

    public void setApprovalState(String approvalState) {
        this.approvalState = approvalState;
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

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Date getStartCreatTime() {
        return startCreatTime;
    }

    public void setStartCreatTime(Date startCreatTime) {
        this.startCreatTime = startCreatTime;
    }

    public Date getEndCreaateTime() {
        return endCreaateTime;
    }

    public void setEndCreaateTime(Date endCreaateTime) {
        this.endCreaateTime = endCreaateTime;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
