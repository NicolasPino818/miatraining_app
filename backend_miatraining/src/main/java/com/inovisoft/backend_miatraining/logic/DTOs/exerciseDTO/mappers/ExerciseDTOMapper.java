package com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.mappers;

import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.ExerciseDTO;
import com.inovisoft.backend_miatraining.models.ExerciseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ExerciseDTOMapper implements Function<ExerciseModel, ExerciseDTO> {
    @Autowired
    ExerciseCategoryDTOMapper exerciseCategoryDTOMapper;
    @Override
    public ExerciseDTO apply(ExerciseModel exerciseModel) {
        return ExerciseDTO
                .builder()
                .id(exerciseModel.getExerciseID())
                .name(exerciseModel.getExerciseName())
                .description(exerciseModel.getDescription())
                .tutorialSrc(exerciseModel.getTutorialLink())
                .imageSrc(exerciseModel.getImageLink())
                .trainingType(exerciseModel.getTrainingType().getTrainingTypeName())
                .exerciseCategories(
                        exerciseModel.getCategories()
                        .stream().map(exerciseCategoryDTOMapper)
                        .toList())
                .build();
    }
}
