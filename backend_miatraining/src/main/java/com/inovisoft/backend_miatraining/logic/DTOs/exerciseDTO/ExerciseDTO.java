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
public class ExerciseDTO {
    private Long id;
    private String name;
    private String tutorialSrc;
    private String imageSrc;
    private String description;
    private String trainingType;
    private List<ExerciseCategoryDTO> exerciseCategories;
}
