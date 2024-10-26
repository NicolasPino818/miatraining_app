package com.inovisoft.backend_miatraining.logic.controllers;

import com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.SaveExerciseToDayRoutineDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.SaveTrainingPlanDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.SaveUsersToPlanDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.TrainingPlanUserDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.response.TrainingPlanResponseDTO;
import com.inovisoft.backend_miatraining.logic.services.TrainingPlanService;
import com.inovisoft.backend_miatraining.models.ExerciseRoutineModel;
import com.inovisoft.backend_miatraining.models.TrainingDayModel;
import com.inovisoft.backend_miatraining.models.TrainingPlanModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/training-plan")
public class TrainingPlanController {

    @Autowired
    TrainingPlanService trainingPlanService;

    // Obtener un TrainingPlan por ID
    @GetMapping("/{id}")
    public ResponseEntity<TrainingPlanResponseDTO> getTrainingPlanById(@PathVariable Long id) {
        return ResponseEntity.ok(trainingPlanService.getTrainingPlanById(id));
    }

    // Obtener un TrainingPlan por Correo
    @GetMapping("/by-email/{email}")
    public ResponseEntity<TrainingPlanResponseDTO> getTrainingPlanByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(trainingPlanService.getTrainingPlanByEmail(email));
    }

    // Obtener todos los TrainingPlans
    @GetMapping
    public ResponseEntity<ArrayList<TrainingPlanResponseDTO>> getAllTrainingPlans() {
        return ResponseEntity.ok(trainingPlanService.getAllTrainingPlans());
    }

    // Crear un nuevo TrainingPlan
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTrainingPlan(@RequestBody SaveTrainingPlanDTO trainingPlan) {
        trainingPlanService.saveTrainingPlan(trainingPlan);
    }

    @PostMapping("/{planId}/routine")
    @ResponseStatus(HttpStatus.CREATED)
    public void addExerciseToPlan(@PathVariable("planId") Long planId,
                                  @RequestBody SaveExerciseToDayRoutineDTO exercise) {
        trainingPlanService.addRoutineToPlan(planId, exercise);
    }

    @DeleteMapping("/routine/{routineID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRoutine(@PathVariable("routineID") Long routineID) {
        trainingPlanService.deleteRoutine(routineID);
    }

    @GetMapping("/{planID}/users")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<TrainingPlanUserDTO>> getUsersByPlanID(@PathVariable("planID") Long planID){
        return ResponseEntity.ok(trainingPlanService.getUsersByPlanID(planID));
    }

    @PostMapping("/{planID}/users")
    @ResponseStatus(HttpStatus.OK)
    public void addUsersToPlan(@PathVariable("planID") Long planID,
                               @RequestBody SaveUsersToPlanDTO dto){
        trainingPlanService.addUsersToPlan(planID,dto);
    }

    @PutMapping("/{planID}/users/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUsersFromPlan(@PathVariable("planID") Long planID,
                               @RequestBody SaveUsersToPlanDTO dto){
        trainingPlanService.deleteUsersFromPlan(planID,dto);
    }

    // Eliminar un TrainingPlan por ID
    @DeleteMapping("/{planId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTrainingPlan(@PathVariable("planId") Long planId) {
        trainingPlanService.deleteTrainingPlanById(planId);
    }

    //Actualizar plan de entrenamiento por ID
    @PutMapping("/routine/{routineID}")
    public ResponseEntity<Void> updateExerciseRoutine(
            @PathVariable Long routineID,
            @RequestBody ExerciseRoutineModel updatedRoutine) {

        // Llamamos al servicio para que realice la actualización
        trainingPlanService.updateExerciseRoutine(routineID, updatedRoutine);

        // Retornar una respuesta indicando que la actualización fue exitosa
        return ResponseEntity.ok().build();
    }



}