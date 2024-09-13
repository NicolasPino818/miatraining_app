package com.inovisoft.backend_miatraining.errorHandlers.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ExistingUserByEmailException extends RuntimeException {
    private HttpStatus status = HttpStatus.BAD_REQUEST;
    private String message = "USER WITH SAME EMAIL ALREADY EXISTS";
    private String code = "auth:duplicated-existing-user-email";
}
