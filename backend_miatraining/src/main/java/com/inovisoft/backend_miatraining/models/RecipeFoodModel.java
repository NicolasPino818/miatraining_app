package com.inovisoft.backend_miatraining.models;

import com.inovisoft.backend_miatraining.repositories.compositeKeys.RecipeFoodId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recipeFood")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeFoodModel {

    @EmbeddedId
    private RecipeFoodId id;

    @Column(nullable = false)
    private Float amount;

    @ManyToOne
    @MapsId("foodID")
    @JoinColumn(name = "foodID", nullable = false)
    private FoodModel foodModel;

    @ManyToOne
    @MapsId("recipeID")
    @JoinColumn(name = "recipeID", nullable = false)
    private RecipeModel recipeModel;
}