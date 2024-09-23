package com.inovisoft.backend_miatraining.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "sale")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "saleID")
    private Long saleID;

    @Column(nullable = false)
    private Float totalPrice;

    @Column(nullable = false)
    private LocalDate saleDate;

    @Column(nullable = false, length = 50)
    private String paymentMethod;

    @Column(nullable = false, length = 20)
    private String state;

    @Column(length = 500)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private UserModel user;
}
