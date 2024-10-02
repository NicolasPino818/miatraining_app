package com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingPlanResponseDTO {
    private Long planID;
    private PlanCreatorDTO planCreator;
    private LocalDate creationDate;
    private Set<TrainingDayDTO> planDays;
}
