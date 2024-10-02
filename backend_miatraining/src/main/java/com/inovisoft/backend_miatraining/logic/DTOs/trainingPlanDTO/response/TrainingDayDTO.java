package com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDayDTO {
    private Long id;
    private Integer dayNumber;
    private Set<DayExerciseDTO> exercises;
}
