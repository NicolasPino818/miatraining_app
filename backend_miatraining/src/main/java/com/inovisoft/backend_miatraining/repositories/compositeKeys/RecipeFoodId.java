package com.inovisoft.backend_miatraining.repositories.compositeKeys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecipeFoodId implements Serializable {

    @Column(name = "foodID")
    private Long foodID;

    @Column(name = "recipeID")
    private Long recipeID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeFoodId that = (RecipeFoodId) o;

        if (!foodID.equals(that.foodID)) return false;
        return recipeID.equals(that.recipeID);
    }

    @Override
    public int hashCode() {
        int result = foodID.hashCode();
        result = 31 * result + recipeID.hashCode();
        return result;
    }
}
