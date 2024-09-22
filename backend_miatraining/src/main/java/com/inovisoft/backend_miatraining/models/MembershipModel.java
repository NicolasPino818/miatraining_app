package com.inovisoft.backend_miatraining.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "membership")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membershipID")
    private Long membershipID;

    @Column(nullable = false, length = 150)
    private String membershipName;

    @Column(nullable = false)
    private Integer monthsDuration;

    @Column(nullable = false)
    private Float priceUnit;

    @Column(length = 1500, nullable = false)
    private String description;
}
