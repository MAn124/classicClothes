package com.ttma.classicClothes.dto.response;

import lombok.Getter;

@Getter
public class ResponseData<T> {
    private int status;
    private String message;
    private T data;

    public ResponseData(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseData(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
