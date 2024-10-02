package com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.mappers;

import com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.response.PlanCreatorDTO;
import com.inovisoft.backend_miatraining.models.UserModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PlanCreatorDTOMapper implements Function<UserModel, PlanCreatorDTO> {
    @Override
    public PlanCreatorDTO apply(UserModel userModel) {
        return PlanCreatorDTO
                .builder()
                .name(userModel.getName() + " " + userModel.getSurname())
                .email(userModel.getEmail())
                .build();
    }
}
