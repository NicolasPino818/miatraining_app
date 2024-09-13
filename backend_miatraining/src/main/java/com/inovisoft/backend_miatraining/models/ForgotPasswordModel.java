package com.inovisoft.backend_miatraining.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "forgot_password")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fpID")
    private Long fpID;

    @Column(nullable = false)
    private Integer otp;

    @Column(nullable = false)
    private Date exp;

    @OneToOne()
    @JoinColumn(name = "userID")
    private UserModel user;
}