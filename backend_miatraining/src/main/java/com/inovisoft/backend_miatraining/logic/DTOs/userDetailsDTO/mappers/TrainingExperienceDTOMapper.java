package com.inovisoft.backend_miatraining.logic.DTOs.userDetailsDTO.mappers;

import com.inovisoft.backend_miatraining.logic.DTOs.userDetailsDTO.ObjectiveDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.userDetailsDTO.TrainingExperienceDTO;
import com.inovisoft.backend_miatraining.models.ObjectiveModel;
import com.inovisoft.backend_miatraining.models.TrainingExperienceModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TrainingExperienceDTOMapper implements Function<TrainingExperienceModel, TrainingExperienceDTO> {
    @Override
    public TrainingExperienceDTO apply(TrainingExperienceModel trainingExperienceModel) {
        return TrainingExperienceDTO
                .builder()
                .id(trainingExperienceModel.getExperienceID())
                .experience(trainingExperienceModel.getExperienceName())
                .description(trainingExperienceModel.getDescription())
                .build();
    }
}
