package com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.mappers;

import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.ExerciseCategoryDTO;
import com.inovisoft.backend_miatraining.models.ExerciseCategoryModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ExerciseCategoryDTOMapper implements Function<ExerciseCategoryModel, ExerciseCategoryDTO> {

    @Override
    public ExerciseCategoryDTO apply(ExerciseCategoryModel exerciseCategoryModel) {
        return ExerciseCategoryDTO
                .builder()
                .id(exerciseCategoryModel.getExerciseCategoryID())
                .categoryName(exerciseCategoryModel.getCategoryName())
                .build();
    }
}
