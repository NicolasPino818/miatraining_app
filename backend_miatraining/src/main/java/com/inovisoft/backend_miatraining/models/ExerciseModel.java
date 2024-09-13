package com.inovisoft.backend_miatraining.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "forgot_password")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exerciseID")
    private Long ejercicioID;

    @Column(length = 150, nullable = false)
    private String exerciseName;

    @Lob
    private String tutorialLink;

    @Lob
    private String imageLink;

    @Lob
    private String description;

    //@ManyToOne
    //@JoinColumn(name = "trainingType_trainingTypeID", nullable = false)
    //private TrainingTypeModel trainingType;
}