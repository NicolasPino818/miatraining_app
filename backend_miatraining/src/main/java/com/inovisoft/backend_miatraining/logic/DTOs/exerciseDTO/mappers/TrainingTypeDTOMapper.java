package com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.mappers;

import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.TrainingTypeDTO;
import com.inovisoft.backend_miatraining.models.TrainingTypeModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TrainingTypeDTOMapper implements Function<TrainingTypeModel, TrainingTypeDTO> {
    @Override
    public TrainingTypeDTO apply(TrainingTypeModel trainingTypeModel) {
        return TrainingTypeDTO
                .builder()
                .id(trainingTypeModel.getTrainingTypeID())
                .type(trainingTypeModel.getTrainingTypeName())
                .build();
    }
}
