package com.whk.resourceserver.dto.response;

import com.whk.resourceserver.dto.common.ResultDTO;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class SignDiaryResponseDTO extends ResultDTO {

    @ApiModelProperty(value = "Date",example = "第一签到时间" )
    private Date signTime_first;

    @ApiModelProperty(value = "Date",example = "最后签到时间" )
    private Date signTime_last;

    @ApiModelProperty(value = "Date",example = "签到时间" )
    private Date signTime;

    @ApiModelProperty(value = "Integer",example = "签到状态信息" )
    private String MSG;

    public String getMSG() {
        return MSG;
    }

    public void setMSG(String MSG) {
        this.MSG = MSG;
    }

    public Date getSignTime_first() {
        return signTime_first;
    }

    public void setSignTime_first(Date signTime_first) {
        this.signTime_first = signTime_first;
    }

    public Date getSignTime_last() {
        return signTime_last;
    }

    public void setSignTime_last(Date signTime_last) {
        this.signTime_last = signTime_last;
    }

    public Date getSignTime() {
        return signTime;
    }

    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }
}
