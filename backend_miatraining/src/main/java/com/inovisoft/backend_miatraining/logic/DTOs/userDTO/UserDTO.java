package com.inovisoft.backend_miatraining.logic.DTOs.userDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String fullName;
    private String email;
    private Boolean enabled;
    private Boolean firstLogin;
    private LocalDate registrationDate;
    private String role;
}
