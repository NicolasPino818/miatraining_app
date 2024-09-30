package com.inovisoft.backend_miatraining.errorHandlers.exceptions;

import com.inovisoft.backend_miatraining.logic.DTOs.authDTO.OtpVerificationStatusResponseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class OtpUserDoesNotMatchException extends RuntimeException {
    private HttpStatus status = HttpStatus.UNAUTHORIZED;
    private OtpVerificationStatusResponseDTO verificationStatus = new OtpVerificationStatusResponseDTO(false);
    private String code = "auth:otp-user-verification-failure";
}