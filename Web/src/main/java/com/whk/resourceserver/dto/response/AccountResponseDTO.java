package com.whk.resourceserver.dto.response;

import com.whk.resourceserver.dto.common.ResultDTO;
import com.whk.resourceserver.entity.accountEntity;
import io.swagger.annotations.ApiModelProperty;

public class AccountResponseDTO extends ResultDTO {

    @ApiModelProperty(value = "accountEntity",example = "" )
    private accountEntity accountEntity;

    public com.whk.resourceserver.entity.accountEntity getAccountEntity() {
        return accountEntity;
    }

    public void setAccountEntity(com.whk.resourceserver.entity.accountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }
}
