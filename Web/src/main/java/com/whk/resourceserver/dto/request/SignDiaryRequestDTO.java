package com.whk.resourceserver.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class SignDiaryRequestDTO implements Serializable {
    private static final long serialVersionUID = -31541153211787458L;

    @ApiModelProperty(required = true ,value = "String" , example = "wanghongkun")
    private String accountnumber;

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }
}
