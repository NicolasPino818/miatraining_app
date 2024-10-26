package com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingPlanUserDTO {
    private Long id;
    private String fullName;
    private String name;
    private String surname;
    private String profilePictureLink;
    private String email;
}
