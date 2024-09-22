package com.inovisoft.backend_miatraining.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "saleDetail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "saleDetailID")
    private Long saleDetailID;

    @Column(nullable = false)
    private Integer unitPrice;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer subtotal;

    @ManyToOne
    @JoinColumn(name = "saleID", nullable = false)
    private SaleModel sale;

    @ManyToOne
    @JoinColumn(name = "membershipID", nullable = false)
    private MembershipModel membership;
}
