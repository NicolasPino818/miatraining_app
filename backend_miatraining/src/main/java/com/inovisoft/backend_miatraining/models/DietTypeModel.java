package com.inovisoft.backend_miatraining.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "dietType")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DietTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dietTypeID")
    private Long dietTypeID;

    @Column(nullable = false, length = 150)
    private String dietType;
}
