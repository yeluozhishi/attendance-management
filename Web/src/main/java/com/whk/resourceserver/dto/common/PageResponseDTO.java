package com.whk.resourceserver.dto.common;


import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class PageResponseDTO<T> extends ResultDTO{

    @ApiModelProperty("总数据数")
    private long totalElements;

    @ApiModelProperty("总页数")
    private Integer totalPages;

    @ApiModelProperty("当前页")
    private Integer pageNo;

    @ApiModelProperty("当前页数据数")
    private Integer pageSize;

    @ApiModelProperty("数据页内容")
    List<T> content;

    public void setTotalElement(long totalElement) {
        this.totalElements=totalElement;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages=totalPages;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setPageNo(Integer PageNo) {
        this.pageNo=pageNo;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize=pageSize;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setContent(List<T> content) {
        this.content=content;
    }

    public List<T> getContent() {
        return content;
    }
}
