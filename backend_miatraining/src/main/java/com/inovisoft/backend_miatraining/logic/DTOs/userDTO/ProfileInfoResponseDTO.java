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
public class ProfileInfoResponseDTO {
    private String name;
    private String surname;
    private String fullName;
    private String email;
    private String pictureUrlString;
    private LocalDate RegistrationDate;
}
