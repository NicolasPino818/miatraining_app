package com.inovisoft.backend_miatraining.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "trainingType")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainingTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trainingTypeID")
    private Long trainingTypeID;

    @Column(nullable = false, length = 150)
    private String trainingTypeName;

    @Column(length = 250, nullable = false)
    private String description;
}
