package com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.response;

import com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.TrainingPlanUserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrainingPlanResponseDTO {
    private Long planID;
    private PlanCreatorDTO planCreator;
    private LocalDate creationDate;
    private List<TrainingPlanUserDTO> planUsers;
    private Set<TrainingDayDTO> planDays;
}
