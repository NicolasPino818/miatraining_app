package com.inovisoft.backend_miatraining.logic.trainingPlan.services;

import com.inovisoft.backend_miatraining.models.TrainingPlanModel;
import com.inovisoft.backend_miatraining.repositories.trainingPlan.TrainingPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainingPlanService {

    @Autowired
    TrainingPlanRepository trainingPlanRepository;

    // Obtener un TrainingPlan por ID
    public Optional<TrainingPlanModel> getTrainingPlanById(Long id) {
        return trainingPlanRepository.findById(id);
    }

    // Obtener todos los TrainingPlans
    public Iterable<TrainingPlanModel> getAllTrainingPlans() {
        return trainingPlanRepository.findAll();
    }

    // Crear o actualizar un TrainingPlan
    public TrainingPlanModel saveTrainingPlan(TrainingPlanModel trainingPlan) {
        return trainingPlanRepository.save(trainingPlan);
    }

    // Eliminar un TrainingPlan por ID
    public void deleteTrainingPlanById(Long id) {
        trainingPlanRepository.deleteById(id);
    }
}
