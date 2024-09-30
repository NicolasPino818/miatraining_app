package com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.mappers;

import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.ExerciseCategoryDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.ExerciseCategoryPageResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ExerciseCategoryPageResponseDTOMapper
        implements Function<Page<ExerciseCategoryDTO>, ExerciseCategoryPageResponseDTO> {
    @Override
    public ExerciseCategoryPageResponseDTO apply(Page<ExerciseCategoryDTO> exerciseCategoryDTOS) {
        return ExerciseCategoryPageResponseDTO
                .builder()
                .exerciseCategories(exerciseCategoryDTOS.toList())
                .pageSize(exerciseCategoryDTOS.getSize())
                .pageNumber(exerciseCategoryDTOS.getNumber())
                .build();
    }
}
