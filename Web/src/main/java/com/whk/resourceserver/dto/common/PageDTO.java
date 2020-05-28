package com.whk.resourceserver.dto.common;

import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;

@ApiModel(value = "分页对象",description = "用于描述分页信息")
public class PageDTO {

    @NotNull
    @Min(value = 1,message = "页数不能小于一")
    @ApiModelProperty(value = "页数",name = "pageSize",example = "1",required = true,dataType = "int")
    private int pageNo;


}
