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
public class ClientTrainerId implements Serializable {

    @Column(name = "clientUserID")
    private Long clientUserID;

    @Column(name = "trainerUserID")
    private Long trainerUserID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientTrainerId that = (ClientTrainerId) o;

        if (!clientUserID.equals(that.clientUserID)) return false;
        return trainerUserID.equals(that.trainerUserID);
    }

    @Override
    public int hashCode() {
        int result = clientUserID.hashCode();
        result = 31 * result + trainerUserID.hashCode();
        return result;
    }

}
