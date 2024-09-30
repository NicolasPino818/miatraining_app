package com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO;

import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveTrainingPlanDTO {
    Long creatorUserID;
}
