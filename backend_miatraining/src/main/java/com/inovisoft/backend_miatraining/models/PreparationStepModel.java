package com.inovisoft.backend_miatraining.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "preparationStep")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreparationStepModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stepID")
    private Long stepID;

    @Column(nullable = false)
    private Integer stepNumber;

    @Column(length = 500, nullable = false)
    private String stepDescription;

    @ManyToOne
    @JoinColumn(name = "recipeID", nullable = false)
    private RecipeModel recipe;
}
