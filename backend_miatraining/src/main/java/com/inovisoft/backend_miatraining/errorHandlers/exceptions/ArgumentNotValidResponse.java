package com.inovisoft.backend_miatraining.errorHandlers.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
public class ArgumentNotValidResponse {

    private LocalDateTime time = LocalDateTime.now();
    private String resourceName;
    private String method;
    private Map<String, String> message;
    private String code = "client:missing-body-parameter";

    public ArgumentNotValidResponse(String resourceName, String method,Map<String, String> message){
        this.message = message;
        this.resourceName = resourceName.replace("uri=","");
        this.method = method;
    }
}
