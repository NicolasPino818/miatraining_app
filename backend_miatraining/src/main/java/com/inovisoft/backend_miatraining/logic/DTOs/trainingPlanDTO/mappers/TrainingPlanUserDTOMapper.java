package com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.mappers;

import com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.TrainingPlanUserDTO;
import com.inovisoft.backend_miatraining.models.UserModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TrainingPlanUserDTOMapper implements Function<UserModel, TrainingPlanUserDTO> {
    @Override
    public TrainingPlanUserDTO apply(UserModel userModel) {
        return TrainingPlanUserDTO
                .builder()
                .id(userModel.getUserID())
                .fullName(userModel.getName() + " " + userModel.getSurname())
                .name(userModel.getName())
                .surname(userModel.getSurname())
                .profilePictureLink(userModel.getPictureUrlString())
                .email(userModel.getEmail())
                .build();
    }
}
