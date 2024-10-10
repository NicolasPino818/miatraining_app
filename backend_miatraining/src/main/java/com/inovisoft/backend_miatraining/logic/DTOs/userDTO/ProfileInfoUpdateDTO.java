package com.inovisoft.backend_miatraining.logic.DTOs.userDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileInfoUpdateDTO {
    private String email;
    private String pictureUrlString;
}
