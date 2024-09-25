package com.inovisoft.backend_miatraining.repositories.trainingPlan;

import com.inovisoft.backend_miatraining.models.TrainingPlanModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingPlanRepository extends CrudRepository<TrainingPlanModel, Long> {
    // Consultas si se necesita
}
