package com.inovisoft.backend_miatraining.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "exerciseCategory")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseCategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exerciseCategoryID")
    private Long exerciseCategoryID;

    @Column(length = 50, nullable = false)
    private String categoryName;
}