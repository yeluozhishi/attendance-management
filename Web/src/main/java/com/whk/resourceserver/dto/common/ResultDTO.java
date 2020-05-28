package com.whk.resourceserver.dto.common;

import com.whk.resourceserver.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api("结果状态实体")
public class ResultDTO {

    @ApiModelProperty("状态码")
    private String code;

    @ApiModelProperty("状态信息")
    private String message;

    public ResultDTO() {
        this.code= Result.SUCCESS.CODE;
        this.message=Result.SUCCESS.MSG;
    }

    public void setCode(String code) {
        this.code=code;
    }

    public String getCode() {
        return code;
    }

    public void setMessage(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}
