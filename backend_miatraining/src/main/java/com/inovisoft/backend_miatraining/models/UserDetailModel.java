package com.inovisoft.backend_miatraining.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Table(name = "userDetail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userDetailID")
    private Long userDetailID;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Float height;

    @Column(nullable = false)
    private Float currentWeight;

    @Column(nullable = false, length = 1)
    private char sex;

    @Column(length = 500, nullable = false)
    private String profilePictureFront;

    @Column(length = 500, nullable = false)
    private String profilePictureBack;

    @Column(length = 500, nullable = false)
    private String profilePictureSide;

    @ManyToOne
    @JoinColumn(name = "objectiveID", nullable = false)
    private ObjectiveModel objective;

    @ManyToOne
    @JoinColumn(name = "bodyTypeID", nullable = false)
    private BodyTypeModel bodyType;

    @ManyToOne
    @JoinColumn(name = "dietTypeID", nullable = false)
    private DietTypeModel dietType;

    @ManyToOne
    @JoinColumn(name = "experienceID", nullable = false)
    private TrainingExperienceModel trainingExperience;

    @ManyToOne
    @JoinColumn(name = "trainingTypeID", nullable = false)
    private TrainingTypeModel trainingType;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private UserModel user;
}
