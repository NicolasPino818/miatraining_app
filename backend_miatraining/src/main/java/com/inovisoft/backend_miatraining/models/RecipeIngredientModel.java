package com.inovisoft.backend_miatraining.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "recipeIngredient")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredientModel {

    @EmbeddedId
    private IngredientRecipeId id;

    @Column(nullable = false)
    private Float amount;

    @ManyToOne
    @MapsId("ingredientID")
    @JoinColumn(name = "ingredientID", nullable = false)
    private IngredientModel ingredientModel;  // Relación con la tabla de ingredientes

    @ManyToOne
    @MapsId("ingredientID")
    @JoinColumn(name = "ingredientID", nullable = false)
    private IngredientModel ingredientModel;  // Relación con la tabla de ingredientes
}