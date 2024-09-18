package com.inovisoft.backend_miatraining.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "TrainingPlanModel")
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