package com.inovisoft.backend_miatraining.repositories;

import com.inovisoft.backend_miatraining.models.TrainingPlanModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITrainingPlanRepo extends CrudRepository<TrainingPlanModel, Long> {
    @Query("SELECT up.trainingPlan FROM UserPlanModel up WHERE up.user.email = ?1")
    Optional<TrainingPlanModel> findByUserEmail(String email);
}