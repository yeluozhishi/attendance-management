package com.whk.site.domain.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class MemorandumRequestDTO implements Serializable {

    private static final long serialVersionUID = -152757986007195977L;

    private String accountnumber;

    private String title;

    private String content;

    private Date belongDate;

    private Integer pageNo;

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

    public Date getBelongDate() {
        return belongDate;
    }

    public void setBelongDate(Date belongDate) {
        this.belongDate = belongDate;
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
}
