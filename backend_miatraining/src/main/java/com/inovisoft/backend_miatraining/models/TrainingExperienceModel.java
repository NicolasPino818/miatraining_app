package com.inovisoft.backend_miatraining.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trainingExperience")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingExperienceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "experienceID")
    private Long experienceID;

    @Column(nullable = false, length = 150)
    private String experienceName;

    @Column(length = 250)
    private String description;

}
