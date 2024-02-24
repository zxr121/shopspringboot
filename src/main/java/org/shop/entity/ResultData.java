package org.shop.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultData<T> implements Serializable {
    private Integer code = 0;
    private String message = "";
    private T data;

    public ResultData() {

    }

    public ResultData(T data) {
        this.data = data;
    }

    public ResultData(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultData(Integer code, String message, T data) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

}
