package com.whk.resourceserver.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class MemorandumRequestDTO implements Serializable {
    private static final long serialVersionUID = 8442369529220791281L;

    @ApiModelProperty(required = false,value = "Integer",example = "1")
    private Integer Id;

    @ApiModelProperty(required = true,value = "String",example = "wanghongkun")
    private String accountnumber;

    @ApiModelProperty(required = false,value = "String",example = "标题")
    private String title;

    @ApiModelProperty(required = false,value = "String",example = "内容")
    private String content;

    @ApiModelProperty(required = false,value = "Date",example = "所属日期")
    private Date belongDate;

    @ApiModelProperty(required = false,value = "Date",example = "所属日期开始")
    private Date belongDateStart;

    @ApiModelProperty(required = false,value = "Date",example = "所属日期结束")
    private Date belongDateEnd;

    @ApiModelProperty(required = false,value = "Date",example = "更新日期开始")
    private Date updataTimeStart;

    @ApiModelProperty(required = false,value = "Date",example = "更新日期结束")
    private Date updataTimeEnd;

    @ApiModelProperty(required = false, value = "页号",example = "1")
    private Integer pageNo;

    @ApiModelProperty(required = false, value = "页面大小",example = "10")
    private Integer pageSize;

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Date getBelongDateStart() {
        return belongDateStart;
    }

    public void setBelongDateStart(Date belongDateStart) {
        this.belongDateStart = belongDateStart;
    }

    public Date getBelongDateEnd() {
        return belongDateEnd;
    }

    public void setBelongDateEnd(Date belongDateEnd) {
        this.belongDateEnd = belongDateEnd;
    }

    public Date getUpdataTimeStart() {
        return updataTimeStart;
    }

    public void setUpdataTimeStart(Date updataTimeStart) {
        this.updataTimeStart = updataTimeStart;
    }

    public Date getUpdataTimeEnd() {
        return updataTimeEnd;
    }

    public void setUpdataTimeEnd(Date updataTimeEnd) {
        this.updataTimeEnd = updataTimeEnd;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Date getBelongDate() {
        return belongDate;
    }

    public void setBelongDate(Date belongDate) {
        this.belongDate = belongDate;
    }
}
