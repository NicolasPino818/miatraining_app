package com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.mappers;

import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.ExerciseDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.ExercisePageResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ExercisePageResponseDTOMapper  implements Function<Page<ExerciseDTO>, ExercisePageResponseDTO> {
    @Override
    public ExercisePageResponseDTO apply(Page<ExerciseDTO> exerciseDTOS) {
        return ExercisePageResponseDTO
                .builder()
                .pageNumber(exerciseDTOS.getNumber())
                .pageSize(exerciseDTOS.getSize())
                .exercises(exerciseDTOS.toList())
                .build();
    }
}
