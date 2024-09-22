package com.inovisoft.backend_miatraining.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "objective")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObjectiveModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "objectiveID")
    private Long objectiveID;

    @Column(nullable = false, length = 150)
    private String objectiveName;

    @Column(length = 250, nullable = false)
    private String description;

}
