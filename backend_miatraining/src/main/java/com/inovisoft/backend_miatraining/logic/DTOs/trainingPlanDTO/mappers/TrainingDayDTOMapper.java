package com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.mappers;

import com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.response.TrainingDayDTO;
import com.inovisoft.backend_miatraining.models.TrainingDayModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TrainingDayDTOMapper implements Function<TrainingDayModel,TrainingDayDTO> {
    @Autowired
    DayExerciseDTOMapper dayExerciseDTOMapper;
    @Override
    public TrainingDayDTO apply(TrainingDayModel trainingDayModel) {
        return TrainingDayDTO
                .builder()
                .id(trainingDayModel.getDayID())
                .dayNumber(trainingDayModel.getDayNumber())
                .exercises(trainingDayModel.getExerciseRoutines()
                        .stream().map(dayExerciseDTOMapper).collect(Collectors.toSet()))
                .build();
    }
}
