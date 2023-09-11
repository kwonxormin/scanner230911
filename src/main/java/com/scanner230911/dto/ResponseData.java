package com.scanner230911.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseData {
    private int code;
    private String msg;
    private Object data;

    @Builder
    public ResponseData(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}