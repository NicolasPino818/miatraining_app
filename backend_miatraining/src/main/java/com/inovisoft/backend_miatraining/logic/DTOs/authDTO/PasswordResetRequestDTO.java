package com.inovisoft.backend_miatraining.logic.DTOs.authDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetRequestDTO {
    @NotBlank(message = "MUST SEND A PASSWORD")
    @Size(min = 8, max = 20, message = "PASSWORD LENGTH IS BETWEEN 8 AND 20 CHARACTERS")
    private String password;
    @NotBlank(message = "MUST SEND A REPEAT PASSWORD")
    @Size(min = 8, max = 20, message = "REPEAT PASSWORD LENGTH IS BETWEEN 8 AND 20 CHARACTERS")
    private String repeat_password;
}