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
public class UsuarioPlanId implements Serializable {

    @Column(name = "userID")
    private Long userID;

    @Column(name = "planID")
    private Long planID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsuarioPlanId that = (UsuarioPlanId) o;

        if (!userID.equals(that.userID)) return false;
        return planID.equals(that.planID);
    }

    @Override
    public int hashCode() {
        Integer result = userID.hashCode();
        result = 31 * result + planID.hashCode();
        return result;
    }
}