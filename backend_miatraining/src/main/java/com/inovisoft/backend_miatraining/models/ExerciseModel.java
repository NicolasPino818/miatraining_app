package com.inovisoft.backend_miatraining.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "exercise")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exerciseID")
    private Long exerciseID;

    @Column(length = 150, nullable = false)
    private String exerciseName;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String tutorialLink;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String imageLink;

    @Column(length = 500)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "category_exercise",
            joinColumns = @JoinColumn(name = "exerciseID", referencedColumnName = "exerciseID"),
            inverseJoinColumns = @JoinColumn(name = "exerciseCategoryID", referencedColumnName = "exerciseCategoryID")
    )
    private List<ExerciseCategoryModel> categories;

    @ManyToOne
    @JoinColumn(name = "trainingTypeID", nullable = false)
    private TrainingTypeModel trainingType;

    @OneToMany(mappedBy = "exercise")
    @JsonManagedReference
    private List<ExerciseRoutineModel> exerciseRoutines;

}