package com.whk.resourceserver.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class notice_getRequestDTO implements Serializable {
    private static final long serialVersionUID = 6349182102013230269L;

    @ApiModelProperty(required = false,value = "int",example = "1")
    private Integer id;

    @ApiModelProperty(required = false,value = "String",example = "汪宏坤")
    private String name;

    @ApiModelProperty(required = false,value = "String",example = "test")
    private String content;

    @ApiModelProperty(required = false,value = "String",example = "manager")
    private String account;

    @ApiModelProperty(required = false,value = "String",example = "")
    private String condition;

    @ApiModelProperty(required = false,value = "Date",example = "")
    private Date createTime;

    @ApiModelProperty(required = false,value = "int",example = "5")
    private int pageSize;

    @ApiModelProperty(required = false,value = "int",example = "1")
    private int pageNo;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
