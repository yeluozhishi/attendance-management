package com.whk.site.util;

public enum Result {
    /**
     * 成功状态
     */
    SUCCESS("success","10000"),

    /**
     * 异常状态
     */
    SYSERR("system error","10001"),

    /**
     * 参数异常
     */
    PARAMERR("param error","10002"),

    /**
     * 资源不存在
     */
    NOT_EXIT_RESOURCE("not exit resource","21001");

    /**
     * 状态码
     */
    public String CODE;

    /**
     * 状态信息
     */
    public String MSG;
    private Result(String msg, String code) {
        this.MSG=msg;
        this.CODE=code;
    }
}
