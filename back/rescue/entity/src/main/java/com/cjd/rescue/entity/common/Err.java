package com.cjd.rescue.entity.common;

public enum Err {

    SUCCESS("000000","请求成功"),
    DB_ERR("000001","数据库请求异常"),
    AUTH_ERR("000002","账号密码错误"),
    NONE_TEAM("000003","当前用户未设置战队"),
    NONE_TASK_PROCESS("000004","未设置任务进度"),


    ;

    private String code;

    private String msg;

    Err(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
