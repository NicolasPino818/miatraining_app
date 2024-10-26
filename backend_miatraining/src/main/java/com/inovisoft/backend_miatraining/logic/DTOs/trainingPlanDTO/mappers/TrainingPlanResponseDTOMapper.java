package com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.mappers;

import com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.response.TrainingDayDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.response.TrainingPlanResponseDTO;
import com.inovisoft.backend_miatraining.models.TrainingDayModel;
import com.inovisoft.backend_miatraining.models.TrainingPlanModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TrainingPlanResponseDTOMapper implements Function<TrainingPlanModel, TrainingPlanResponseDTO> {
    @Autowired
    PlanCreatorDTOMapper planCreatorDTOMapper;
    @Autowired
    TrainingDayDTOMapper trainingDayDTOMapper;
    @Autowired
    TrainingPlanUserDTOMapper trainingPlanUserDTOMapper;

    @Override
    public TrainingPlanResponseDTO apply(TrainingPlanModel trainingPlanModel) {
        return TrainingPlanResponseDTO
                .builder()
                .planID(trainingPlanModel.getPlanID())
                .creationDate(trainingPlanModel.getCreationDate())
                .planCreator(planCreatorDTOMapper.apply(trainingPlanModel.getUser()))
                .planDays(trainingPlanModel.getDays()
                        .stream().map(trainingDayDTOMapper).collect(Collectors.toSet()))
                .planUsers(trainingPlanModel.getUserPlan()
                        .stream().map(userPlanModel ->
                                trainingPlanUserDTOMapper.apply(userPlanModel.getUser())).toList())
                .build();
    }
}
