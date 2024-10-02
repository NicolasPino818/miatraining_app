package com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DayExerciseDTO {
    private Long routineID;
    private Long exerciseID;
    private Integer series;
    private Integer reps;
    private Integer restMinutes;
    private String exerciseName;
    private String tutorialLink;
    private String imageLink;
    private String description;
}
