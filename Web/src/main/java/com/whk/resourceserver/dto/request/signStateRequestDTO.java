package com.whk.resourceserver.dto.request;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class signStateRequestDTO implements Serializable {
    private static final long serialVersionUID = -3961704533127786781L;

    @ApiModelProperty(required = true ,value = "String" , example = "wanghongkun")
    private String accountnumber;

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }
}
