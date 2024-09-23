package com.inovisoft.backend_miatraining.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @MapsId("userID")
    @JoinColumn(name = "userID", nullable = false)
    private UserModel user;  // Relación con la tabla de usuarios

    @Column(nullable = false)
    private LocalDate assignmentDate;
}
