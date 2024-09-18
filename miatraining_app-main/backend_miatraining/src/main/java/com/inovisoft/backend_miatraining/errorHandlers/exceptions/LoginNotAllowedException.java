package com.inovisoft.backend_miatraining.errorHandlers.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class LoginNotAllowedException extends RuntimeException{
    private HttpStatus status = HttpStatus.FORBIDDEN;
    private String message = "USER IS NOT ALLOWED TO LOGIN";
    private String code = "auth:login-forbidden";
}