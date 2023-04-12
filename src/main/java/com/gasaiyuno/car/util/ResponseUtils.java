package com.gasaiyuno.car.util;


import com.gasaiyuno.car.constants.ReturnCode;

public class ResponseUtils<T> {
    private int code;// 200 成功 600 ***添加失败 400 ***添加失败
    private String message;
    private T data;

    public ResponseUtils(T data) {
        this.code = ReturnCode.success_code;
        this.message = "操作成功";
        this.data = data;
    }

    public ResponseUtils(int code) {
        this.code = code;
        this.message = "操作失败";
    }

    public ResponseUtils(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseUtils(int code, String message, T data) {
        super();

        this.code = code;
        this.message = message;
        this.data = data;

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseUtils{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

}
