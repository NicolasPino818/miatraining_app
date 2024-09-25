package com.inovisoft.backend_miatraining.repositories;

import com.inovisoft.backend_miatraining.models.TrainingPlanModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrainingPlanRepo extends CrudRepository<TrainingPlanModel, Long> {
    // Consultas si se necesita
}