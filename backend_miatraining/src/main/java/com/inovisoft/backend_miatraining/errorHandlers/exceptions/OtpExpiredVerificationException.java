package com.inovisoft.backend_miatraining.errorHandlers.exceptions;

import com.inovisoft.backend_miatraining.logic.DTOs.authDTO.ResetPasswordStatusResponseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class OtpExpiredVerificationException extends RuntimeException {
    private HttpStatus status = HttpStatus.EXPECTATION_FAILED;
    private ResetPasswordStatusResponseDTO verificationStatus = new ResetPasswordStatusResponseDTO(false);
    private String code = "auth:otp-expired-verification-failure";
}
