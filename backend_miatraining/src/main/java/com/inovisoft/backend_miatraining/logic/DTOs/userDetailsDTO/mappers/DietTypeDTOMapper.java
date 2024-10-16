package com.inovisoft.backend_miatraining.logic.DTOs.userDetailsDTO.mappers;

import com.inovisoft.backend_miatraining.logic.DTOs.userDetailsDTO.BodyTypeDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.userDetailsDTO.DietTypeDTO;
import com.inovisoft.backend_miatraining.models.BodyTypeModel;
import com.inovisoft.backend_miatraining.models.DietTypeModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DietTypeDTOMapper implements Function<DietTypeModel, DietTypeDTO> {
    @Override
    public DietTypeDTO apply(DietTypeModel dietTypeModel) {
        return DietTypeDTO
                .builder()
                .id(dietTypeModel.getDietTypeID())
                .diet(dietTypeModel.getDietType())
                .build();
    }
}
