package com.kotlin.retrofit.live.daolai;

public class ResultException extends RuntimeException {

    private String errCode;

    public ResultException(String errCode, String msg) {
        super(msg);
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }
}