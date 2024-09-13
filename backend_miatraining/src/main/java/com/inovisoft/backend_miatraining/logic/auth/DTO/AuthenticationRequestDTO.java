package com.inovisoft.backend_miatraining.logic.auth.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDTO {
    @NotBlank(message = "MUST SEND AN EMAIL")
    @Email(message = "MUST BE AN EMAIL WITH THE RIGHT FORMAT")
    private String email;
    @NotBlank(message = "MUST SEND A PASSWORD")
    private String password;
}