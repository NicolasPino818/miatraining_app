package com.inovisoft.backend_miatraining.errorHandlers.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PasswordsDoesNotMatchException extends RuntimeException {
    private HttpStatus status = HttpStatus.EXPECTATION_FAILED;
    private String message = "PASSWORD AND REPEAT PASSWORD DOES NOT MATCH";
    private String code = "auth:reset-password-failure";
}
