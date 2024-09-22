package com.inovisoft.backend_miatraining.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "ingredient")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ingredientModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredientID")
    private Long ingredientID;

    @Column(length = 100, nullable = false)
    private String ingredientName;

    @Column(length = 500, nullable = false)
    private String description;

    @Column(nullable = false)
    private Float protein;

    @Column(nullable = false)
    private Float carbohydrates;

    @Column(nullable = false)
    private Float fats;

    @Column(nullable = false)
    private Float basePortion;

    @ManyToOne
    @JoinColumn(name = "measurementUnitID", nullable = false)
    private MeasurementUnitModel measurementUnit;










}