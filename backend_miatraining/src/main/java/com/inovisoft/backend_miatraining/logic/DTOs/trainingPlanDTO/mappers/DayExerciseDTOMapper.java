package com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.mappers;

import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.mappers.ExerciseCategoryDTOMapper;
import com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.response.DayExerciseDTO;
import com.inovisoft.backend_miatraining.models.ExerciseRoutineModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DayExerciseDTOMapper implements Function<ExerciseRoutineModel, DayExerciseDTO> {
    @Autowired
    ExerciseCategoryDTOMapper exerciseCategoryDTOMapper;
    @Override
    public DayExerciseDTO apply(ExerciseRoutineModel exerciseRoutineModel) {
        return DayExerciseDTO
                .builder()
                .routineID(exerciseRoutineModel.getRoutineID())
                .series(exerciseRoutineModel.getSeries())
                .reps(exerciseRoutineModel.getRepetitions())
                .restSeconds(exerciseRoutineModel.getRestSeconds())
                .exerciseID(exerciseRoutineModel.getExercise().getExerciseID())
                .exerciseName(exerciseRoutineModel.getExercise().getExerciseName())
                .imageLink(exerciseRoutineModel.getExercise().getImageLink())
                .tutorialLink(exerciseRoutineModel.getExercise().getTutorialLink())
                .description(exerciseRoutineModel.getExercise().getDescription())
                .categories(
                        exerciseRoutineModel.getExercise().getCategories()
                                .stream().map(exerciseCategoryDTOMapper)
                                .collect(Collectors.toSet()))
                .build();
    }
}
