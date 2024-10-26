package com.inovisoft.backend_miatraining.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "exerciseRoutine")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseRoutineModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routineID")
    private Long routineID;

    @Column(nullable = false)
    private Integer series;

    @Column(nullable = false)
    private Integer repetitions;

    @Column(nullable = false)
    private Integer restSeconds;

    @ManyToOne
    @JoinColumn(name = "dayID", nullable = false)
    private TrainingDayModel trainingDay;

    @ManyToOne
    @JoinColumn(name = "exerciseID", nullable = false)
    private ExerciseModel exercise;
}
