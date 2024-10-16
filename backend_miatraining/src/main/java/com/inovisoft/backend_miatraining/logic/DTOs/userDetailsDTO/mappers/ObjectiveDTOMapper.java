package com.inovisoft.backend_miatraining.logic.DTOs.userDetailsDTO.mappers;

import com.inovisoft.backend_miatraining.logic.DTOs.userDetailsDTO.ObjectiveDTO;
import com.inovisoft.backend_miatraining.models.ObjectiveModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ObjectiveDTOMapper implements Function<ObjectiveModel, ObjectiveDTO> {
    @Override
    public ObjectiveDTO apply(ObjectiveModel objectiveModel) {
        return ObjectiveDTO
                .builder()
                .id(objectiveModel.getObjectiveID())
                .objective(objectiveModel.getObjectiveName())
                .description(objectiveModel.getDescription())
                .build();
    }
}
