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
public class ExerciseCategoryPageResponseDTO {
    private List<ExerciseCategoryDTO> exerciseCategories;
    private int pageNumber;
    private int pageSize;
}
