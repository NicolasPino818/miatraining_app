package com.inovisoft.backend_miatraining.repositories;

import com.inovisoft.backend_miatraining.models.TrainingDayModel;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ITrainingDayRepo extends CrudRepository<TrainingDayModel, Long> {
    @Query(value = "SELECT d FROM TrainingDayModel d WHERE trainingPlan.planID = ?1")
    ArrayList<TrainingDayModel> findAllDaysByPlanId(Long id);
}