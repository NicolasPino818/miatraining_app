package com.inovisoft.backend_miatraining.logic.controllers;

import com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.SaveExerciseToDayRoutineDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.SaveTrainingPlanDTO;
import com.inovisoft.backend_miatraining.logic.services.TrainingPlanService;
import com.inovisoft.backend_miatraining.models.TrainingDayModel;
import com.inovisoft.backend_miatraining.models.TrainingPlanModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/training_plan")
public class TrainingPlanController {

    @Autowired
    TrainingPlanService trainingPlanService;

    // Obtener un TrainingPlan por ID
    @GetMapping("/{id}")
    public ResponseEntity<TrainingPlanModel> getTrainingPlanById(@PathVariable Long id) {
        return ResponseEntity.ok(trainingPlanService.getTrainingPlanById(id));
    }

    // Obtener todos los TrainingPlans
    @GetMapping
    public Iterable<TrainingPlanModel> getAllTrainingPlans() {
        return trainingPlanService.getAllTrainingPlans();
    }

    // Crear un nuevo TrainingPlan
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTrainingPlan(@RequestBody SaveTrainingPlanDTO trainingPlan) {
        trainingPlanService.saveTrainingPlan(trainingPlan);
    }

    @PostMapping("/{planId}/exercise")
    @ResponseStatus(HttpStatus.OK)
    public List<TrainingDayModel> addExerciseToPlan(@PathVariable("planId") Long planId,
                                                    @RequestBody SaveExerciseToDayRoutineDTO exercise) {
        return trainingPlanService.addExerciseToPlan(planId, exercise);
    }

    // Actualizar un TrainingPlan existente
    /*@PutMapping("/{id}")
    public ResponseEntity<TrainingPlanModel> updateTrainingPlan(@PathVariable Long id, @RequestBody TrainingPlanModel updatedTrainingPlan) {
        Optional<TrainingPlanModel> existingPlan = trainingPlanService.getTrainingPlanById(id);

        if (existingPlan.isPresent()) {
            // No necesitas establecer manualmente el ID, ya viene con el objeto existente
            TrainingPlanModel savedPlan = trainingPlanService.saveTrainingPlan(updatedTrainingPlan);
            return ResponseEntity.ok(savedPlan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    // Eliminar un TrainingPlan por ID
    @DeleteMapping("/{planId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTrainingPlan(@PathVariable("planId") Long planId) {
        trainingPlanService.deleteTrainingPlanById(planId);
    }
}