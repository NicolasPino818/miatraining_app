package com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveUsersToPlanDTO {
    Set<Long> userID;
}
