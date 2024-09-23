package com.inovisoft.backend_miatraining.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private TrainingPlanModel trainingPlan;
}