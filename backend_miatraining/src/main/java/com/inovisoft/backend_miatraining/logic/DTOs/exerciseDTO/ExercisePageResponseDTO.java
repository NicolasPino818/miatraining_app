package com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExercisePageResponseDTO {
    private List<ExerciseDTO> exercises;
    private int pageNumber;
    private int pageSize;
}
