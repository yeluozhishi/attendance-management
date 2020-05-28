package com.whk.resourceserver.dto.response;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class sign_dataResponseDTO {

    @ApiModelProperty(value = "String",example = "" )
    private String averageTime;

    @ApiModelProperty(value = "String",example = "" )
    private String work_overtime;

    @ApiModelProperty(value = "int",example = "" )
    private int offwork;

    @ApiModelProperty(value = "int",example = "" )
    private int signfix;

    @ApiModelProperty(value = "int",example = "" )
    private List<Integer> exceptions;

    @ApiModelProperty(value = "int",example = "" )
    private List<Integer> normals;

    public String getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(String averageTime) {
        this.averageTime = averageTime;
    }

    public String getWork_overtime() {
        return work_overtime;
    }

    public void setWork_overtime(String work_overtime) {
        this.work_overtime = work_overtime;
    }

    public int getOffwork() {
        return offwork;
    }

    public void setOffwork(int offwork) {
        this.offwork = offwork;
    }

    public int getSignfix() {
        return signfix;
    }

    public void setSignfix(int signfix) {
        this.signfix = signfix;
    }

    public List<Integer> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<Integer> exceptions) {
        this.exceptions = exceptions;
    }

    public List<Integer> getNormals() {
        return normals;
    }

    public void setNormals(List<Integer> normals) {
        this.normals = normals;
    }
}
