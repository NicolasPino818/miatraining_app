package com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseSubmitDTO {
    private Long id;
    private String name;
    private String tutorialSrc;
    private MultipartFile imageFile;
    private String description;
    private String trainingType;
    private List<ExerciseCategoryDTO> exerciseCategories;
}
