package com.inovisoft.backend_miatraining.logic.trainingPlan.controller;

import com.inovisoft.backend_miatraining.logic.trainingPlan.services.TrainingPlanService;
import com.inovisoft.backend_miatraining.models.TrainingPlanModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/api/v1/training_plan")
public class TrainingPlanController {

    @Autowired
    TrainingPlanService trainingPlanService;

    // Obtener un TrainingPlan por ID
    @GetMapping("/{id}")
    public ResponseEntity<TrainingPlanModel> getTrainingPlanById(@PathVariable Long id) {
        Optional<TrainingPlanModel> trainingPlan = trainingPlanService.getTrainingPlanById(id);
        return trainingPlan.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener todos los TrainingPlans
    @GetMapping
    public Iterable<TrainingPlanModel> getAllTrainingPlans() {
        return trainingPlanService.getAllTrainingPlans();
    }

    // Crear un nuevo TrainingPlan
    @PostMapping
    public ResponseEntity<TrainingPlanModel> createTrainingPlan(@RequestBody TrainingPlanModel trainingPlan) {
        TrainingPlanModel newTrainingPlan = trainingPlanService.saveTrainingPlan(trainingPlan);
        return ResponseEntity.status(201).body(newTrainingPlan);
    }

    // Actualizar un TrainingPlan existente
    @PutMapping("/{id}")
    public ResponseEntity<TrainingPlanModel> updateTrainingPlan(@PathVariable Long id, @RequestBody TrainingPlanModel updatedTrainingPlan) {
        Optional<TrainingPlanModel> existingPlan = trainingPlanService.getTrainingPlanById(id);

        if (existingPlan.isPresent()) {
            // No necesitas establecer manualmente el ID, ya viene con el objeto existente
            TrainingPlanModel savedPlan = trainingPlanService.saveTrainingPlan(updatedTrainingPlan);
            return ResponseEntity.ok(savedPlan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Eliminar un TrainingPlan por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingPlan(@PathVariable Long id) {
        Optional<TrainingPlanModel> trainingPlan = trainingPlanService.getTrainingPlanById(id);

        if (trainingPlan.isPresent()) {
            trainingPlanService.deleteTrainingPlanById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
