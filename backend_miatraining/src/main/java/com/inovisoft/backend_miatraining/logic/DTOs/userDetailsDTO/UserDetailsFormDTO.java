package com.inovisoft.backend_miatraining.logic.DTOs.userDetailsDTO;

import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.TrainingTypeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsFormDTO {
    private List<BodyTypeDTO> bodyType;
    private List<ObjectiveDTO> objective;
    private List<TrainingExperienceDTO> experience;
    private List<TrainingTypeDTO> trainingType;
    private List<DietTypeDTO> diet;
}
