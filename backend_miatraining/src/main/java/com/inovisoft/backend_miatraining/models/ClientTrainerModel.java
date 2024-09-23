package com.inovisoft.backend_miatraining.models;

import com.inovisoft.backend_miatraining.repositories.compositeKeys.ClientTrainerId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "clientTrainer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientTrainerModel {

    @EmbeddedId
    private ClientTrainerId id;

    @ManyToOne
    @MapsId("clientUserID")
    @JoinColumn(name = "clientUserID", nullable = false)
    private UserModel client;  // Relación con la tabla de usuarios

    @ManyToOne
    @MapsId("trainerUserID")
    @JoinColumn(name = "trainerUserID", nullable = false)
    private UserModel trainer;  // Relación con la tabla de usuarios

    @Column(nullable = false)
    private LocalDate assignmentDate;
}
