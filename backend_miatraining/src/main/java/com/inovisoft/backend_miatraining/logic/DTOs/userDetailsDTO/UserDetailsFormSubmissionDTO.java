package com.inovisoft.backend_miatraining.logic.DTOs.userDetailsDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsFormSubmissionDTO {
    private Long bodyTypeID;
    private Long objectiveID;
    private Long experienceID;
    private Long trainingTypeID;
    private Long dietID;
    private Boolean gender;
    private Integer age;
    private Integer height;
    private Integer wight;
    private String frontalPhotoLink;
    private String sidePhotoLink;
    private String backPhotoLink;
}
