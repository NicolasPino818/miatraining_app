package com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingTypeDTO {
    private Long id;
    private String type;
}
