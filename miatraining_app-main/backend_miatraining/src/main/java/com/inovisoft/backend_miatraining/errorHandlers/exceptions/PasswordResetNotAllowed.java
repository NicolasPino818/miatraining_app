package com.inovisoft.backend_miatraining.errorHandlers.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PasswordResetNotAllowed extends RuntimeException{
    private HttpStatus status = HttpStatus.FORBIDDEN;
    private String message = "USER IS NOT ALLOWED TO RESET PASSWORD";
    private String code = "auth:reset-password-forbidden";
}