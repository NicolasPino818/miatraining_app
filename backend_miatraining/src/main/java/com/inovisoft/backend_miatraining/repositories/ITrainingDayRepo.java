package com.inovisoft.backend_miatraining.repositories;

import com.inovisoft.backend_miatraining.models.TrainingDayModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ITrainingDayRepo extends CrudRepository<TrainingDayModel, Long> {
    @Query(value = "SELECT d FROM TrainingDayModel d WHERE d.trainingPlan.planID = ?1")
    ArrayList<TrainingDayModel> findAllDaysByPlanId(Long id);
}