package com.inovisoft.backend_miatraining.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "TrainingDay")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingDayModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dayID")
    private Long dayID;

    @Column(nullable = false)
    private Integer dayNumber;

    @ManyToOne
    @JoinColumn(name = "planID", nullable = false)
    @JsonBackReference
    private TrainingPlanModel trainingPlan;

    @OneToMany(mappedBy = "trainingDay")
    @JsonManagedReference
    private List<ExerciseRoutineModel> exerciseRoutines;
}