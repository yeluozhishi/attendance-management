package com.whk.resourceserver.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "sign")
public class signEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String accountnumber;

    private Date signinTime;

    private Date signoutTime;

    private Time signDuration;

    private Time extraduration;

    private String exception;

    private String exceptionDetail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSigninTime() {
        return signinTime;
    }

    public void setSigninTime(Date signinTime) {
        this.signinTime = signinTime;
    }

    public Date getSignoutTime() {
        return signoutTime;
    }

    public void setSignoutTime(Date signoutTime) {
        this.signoutTime = signoutTime;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public Time getSignDuration() {
        return signDuration;
    }

    public void setSignDuration(Time signDuration) {
        this.signDuration = signDuration;
    }

    public Time getExtraduration() {
        return extraduration;
    }

    public void setExtraduration(Time extraduration) {
        this.extraduration = extraduration;
    }
}
