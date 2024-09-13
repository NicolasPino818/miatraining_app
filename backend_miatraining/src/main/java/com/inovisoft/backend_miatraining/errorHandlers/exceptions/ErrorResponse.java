package com.inovisoft.backend_miatraining.errorHandlers.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private LocalDateTime time = LocalDateTime.now();
    private String resourceName;
    private String method;
    private String message;
    private String code;

    public ErrorResponse(String resourceName, String method,String message, String code){
        this.message = message;
        this.resourceName = resourceName.replace("uri=","");
        this.method = method;
        this.code = code;
    }
}