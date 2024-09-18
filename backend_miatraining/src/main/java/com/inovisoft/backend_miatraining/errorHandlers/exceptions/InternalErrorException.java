package com.inovisoft.backend_miatraining.errorHandlers.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class InternalErrorException extends RuntimeException {
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    private String message = "INTERNAL SERVER ERROR";
    private String code = "server:internal-error";
}