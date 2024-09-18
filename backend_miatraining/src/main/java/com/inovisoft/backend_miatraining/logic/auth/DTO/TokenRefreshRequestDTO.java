package com.inovisoft.backend_miatraining.logic.auth.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenRefreshRequestDTO {
    @NotNull(message = "MISSING REQUIRED TOKEN")
    private String refresh_token;
}
