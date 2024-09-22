package com.inovisoft.backend_miatraining.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "bodyType")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BodyTypeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bodyTypeID")
    private Long bodyTypeID;

    @Column(nullable = false, length = 150)
    private String bodyTypeName;

    @Column(length = 250, nullable = false)
    private String description;
}
