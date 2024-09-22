package com.inovisoft.backend_miatraining.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "measurementUnit")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeasurementUnitModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "measurementUnitID")
    private Long measurementUnitID;

    @Column(nullable = false)
    private String unitName;
}