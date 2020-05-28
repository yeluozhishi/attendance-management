package com.whk.resourceserver.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "memorandum")
public class memorandumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String accountnumber;

    private String title;

    private String content;

    private Date belongDate;

    private Date updataTime;

    public Date getUpdataTime() {
        return updataTime;
    }

    public void setUpdataTime(Date updataTime) {
        this.updataTime = updataTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
}
