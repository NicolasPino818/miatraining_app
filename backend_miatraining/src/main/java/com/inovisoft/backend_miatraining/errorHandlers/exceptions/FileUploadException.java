package com.inovisoft.backend_miatraining.errorHandlers.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class FileUploadException extends RuntimeException {
    private HttpStatus status = HttpStatus.BAD_REQUEST;
    private String message = "FILE INVALID";
    private String code = "file:file-not-valid";
}