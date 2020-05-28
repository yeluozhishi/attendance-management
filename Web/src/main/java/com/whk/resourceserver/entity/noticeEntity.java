package com.whk.resourceserver.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notice")
public class noticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String accountnumber;

    private Date createTime;

    private String missionContent;

    private String accountnumber_name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getMissionContent() {
        return missionContent;
    }

    public void setMissionContent(String missionContent) {
        this.missionContent = missionContent;
    }

    public String getAccountnumber_name() {
        return accountnumber_name;
    }

    public void setAccountnumber_name(String accountnumber_name) {
        this.accountnumber_name = accountnumber_name;
    }
}
