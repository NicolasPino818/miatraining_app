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
    private int series;

    @Column(nullable = false)
    private int repetitions;

    @Column(nullable = false)
    private int restMinutes;

    @ManyToOne
    @JoinColumn(name = "trainingDay_dayID", nullable = false)
    private TrainingDayModel trainingDayModel;

    @ManyToOne
    @JoinColumn(name = "exercise_exerciseID", nullable = false)
    private ExerciseModel exercise;
}
