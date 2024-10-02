package com.inovisoft.backend_miatraining.repositories;

import com.inovisoft.backend_miatraining.models.TrainingTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITrainingTypeRepo extends JpaRepository<TrainingTypeModel,Long> {
    @Query("SELECT CASE WHEN COUNT(tt) > 0 THEN TRUE ELSE FALSE END FROM TrainingTypeModel tt WHERE tt.trainingTypeName = ?1")
    boolean existsByTrainingTypeName(String name);

    Optional<TrainingTypeModel> findByTrainingTypeName(String trainingTypeName);
}
