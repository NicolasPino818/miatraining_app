package com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveExerciseToDayRoutineDTO {
    private Long exerciseID;
    private Integer dayNumber;
    private Integer series;
    private Integer repetitions;
    private Integer restMin;
}
