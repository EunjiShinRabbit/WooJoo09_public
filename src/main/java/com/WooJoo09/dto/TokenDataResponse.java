package com.WooJoo09.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

//==Response DTO==//
@Data
@AllArgsConstructor
public class TokenDataResponse {
    private String token;
    private String subject;
    private String issued_time;
    private String expired_time;
}
