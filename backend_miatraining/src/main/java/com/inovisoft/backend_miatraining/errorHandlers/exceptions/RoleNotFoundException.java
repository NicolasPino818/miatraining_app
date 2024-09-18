package com.inovisoft.backend_miatraining.errorHandlers.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class RoleNotFoundException extends RuntimeException {
    private HttpStatus status = HttpStatus.NOT_FOUND;
    private String message = "ROLE NOT FOUND";
    private String code = "user:role-not-found";
}