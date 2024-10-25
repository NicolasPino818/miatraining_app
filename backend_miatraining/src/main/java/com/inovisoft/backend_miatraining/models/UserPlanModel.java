package com.inovisoft.backend_miatraining.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.inovisoft.backend_miatraining.repositories.compositeKeys.UsuarioPlanId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "userPlan")  // Nombre de la tabla en la base de datos
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPlanModel{
    @EmbeddedId
    private UsuarioPlanId id;  // Clave compuesta (userID + planID)

    @Column(nullable = false)
    private LocalDate assignmentDate;  // Campo para la fecha de asignación

    @ManyToOne
    @MapsId("userID")
    @JsonBackReference
    @JoinColumn(name = "userID", nullable = false)
    private UserModel user;  // Relación con la tabla de usuarios

    @ManyToOne
    @MapsId("planID")
    @JsonBackReference
    @JoinColumn(name = "planID", nullable = false)
    private TrainingPlanModel trainingPlan;  // Relación con la tabla de planes de entrenamiento
}

