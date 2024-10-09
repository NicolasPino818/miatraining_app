package com.inovisoft.backend_miatraining.errorHandlers.exceptions;

import com.inovisoft.backend_miatraining.logic.DTOs.authDTO.ResetPasswordStatusResponseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class OtpUserDoesNotMatchException extends RuntimeException {
    private HttpStatus status = HttpStatus.UNAUTHORIZED;
    private ResetPasswordStatusResponseDTO verificationStatus = new ResetPasswordStatusResponseDTO(false);
    private String code = "auth:otp-user-verification-failure";
}