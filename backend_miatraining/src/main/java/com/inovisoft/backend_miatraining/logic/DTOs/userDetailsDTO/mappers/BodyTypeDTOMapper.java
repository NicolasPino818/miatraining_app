package com.inovisoft.backend_miatraining.logic.DTOs.userDetailsDTO.mappers;

import com.inovisoft.backend_miatraining.logic.DTOs.userDetailsDTO.BodyTypeDTO;
import com.inovisoft.backend_miatraining.models.BodyTypeModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BodyTypeDTOMapper implements Function<BodyTypeModel, BodyTypeDTO> {
    @Override
    public BodyTypeDTO apply(BodyTypeModel bodyTypeModel) {
        return BodyTypeDTO
                .builder()
                .id(bodyTypeModel.getBodyTypeID())
                .bodyType(bodyTypeModel.getBodyTypeName())
                .description(bodyTypeModel.getDescription())
                .build();
    }
}
