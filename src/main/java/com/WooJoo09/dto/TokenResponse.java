package com.WooJoo09.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

//==Response DTO==//
@Data
@AllArgsConstructor
public class TokenResponse<T> {

    private String code;
    private String msg;
    private T data;
}