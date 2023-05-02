package com.example.zuccknowledge.result;

//实现响应的枚举类
public enum ResponseMsg {
    SUCCESS("200", "操作成功"),
    FAILED("999999", "操作失败"),
    ParamError("000001", "参数错误！"),
    FileEmpty("000400", "上传文件为空"),
    ;

    private String code;
    private String msg;
    private ResponseMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

