package com.WooJoo09.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

//==Response DTO==//
@Data
@AllArgsConstructor
public class TokenResponseNoData<T> {
    private String code;
    private String msg;
}
