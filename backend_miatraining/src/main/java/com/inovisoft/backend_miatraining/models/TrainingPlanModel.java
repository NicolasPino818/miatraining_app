package com.inovisoft.backend_miatraining.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "TrainingPlan")
@Table(name = "TrainingPlan")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingPlanModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planID")
    private Long planID;

    @Column(nullable = false)
    private LocalDate creationDate;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private UserModel user;

    @OneToMany(mappedBy = "trainingPlan", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<TrainingDayModel> days;

    @OneToMany(mappedBy = "trainingPlan")
    @JsonManagedReference
    private List<UserPlanModel> userPlan;

}