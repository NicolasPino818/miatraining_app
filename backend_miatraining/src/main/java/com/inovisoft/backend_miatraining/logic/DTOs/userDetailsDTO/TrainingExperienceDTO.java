package com.inovisoft.backend_miatraining.logic.DTOs.userDetailsDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingExperienceDTO {
    private Long id;
    private String experience;
    private String description;
}