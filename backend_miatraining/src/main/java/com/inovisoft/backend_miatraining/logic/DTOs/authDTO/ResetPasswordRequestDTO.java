package com.inovisoft.backend_miatraining.logic.DTOs.authDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequestDTO {
    private String currentPassword;
    private String newPassword;
    private String repeatPassword;
}
