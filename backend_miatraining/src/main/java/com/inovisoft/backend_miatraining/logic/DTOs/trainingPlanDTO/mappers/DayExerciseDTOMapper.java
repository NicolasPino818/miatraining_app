package com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.mappers;

import com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.response.DayExerciseDTO;
import com.inovisoft.backend_miatraining.models.ExerciseRoutineModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DayExerciseDTOMapper implements Function<ExerciseRoutineModel, DayExerciseDTO> {
    @Override
    public DayExerciseDTO apply(ExerciseRoutineModel exerciseRoutineModel) {
        return DayExerciseDTO
                .builder()
                .routineID(exerciseRoutineModel.getRoutineID())
                .series(exerciseRoutineModel.getSeries())
                .reps(exerciseRoutineModel.getRepetitions())
                .restMinutes(exerciseRoutineModel.getRestMinutes())
                .exerciseID(exerciseRoutineModel.getExercise().getExerciseID())
                .exerciseName(exerciseRoutineModel.getExercise().getExerciseName())
                .imageLink(exerciseRoutineModel.getExercise().getImageLink())
                .tutorialLink(exerciseRoutineModel.getExercise().getTutorialLink())
                .description(exerciseRoutineModel.getExercise().getDescription())
                .build();
    }
}
