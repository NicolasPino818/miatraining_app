package com.inovisoft.backend_miatraining.logic.services;

import com.inovisoft.backend_miatraining.errorHandlers.exceptions.ResourceNotFoundException;
import com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.SaveExerciseToDayRoutineDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.SaveTrainingPlanDTO;
import com.inovisoft.backend_miatraining.logic.controllers.UserService;
import com.inovisoft.backend_miatraining.models.TrainingDayModel;
import com.inovisoft.backend_miatraining.models.TrainingPlanModel;
import com.inovisoft.backend_miatraining.repositories.ITrainingDayRepo;
import com.inovisoft.backend_miatraining.repositories.ITrainingPlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TrainingPlanService {

    @Autowired
    ITrainingPlanRepo trainingPlanRepo;
    @Autowired
    ITrainingDayRepo trainingPlanDayRepo;
    @Autowired
    UserService userService;

    // Obtener un TrainingPlan por ID
    public TrainingPlanModel getTrainingPlanById(Long id) {
        return trainingPlanRepo.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    // Obtener todos los TrainingPlans
    public Iterable<TrainingPlanModel> getAllTrainingPlans() {
        return trainingPlanRepo.findAll();
    }

    // Crear o actualizar un TrainingPlan
    public void saveTrainingPlan(SaveTrainingPlanDTO trainingPlanDTO) {

        TrainingPlanModel trainingPlanModel =
                TrainingPlanModel
                        .builder()
                        .creationDate(LocalDate.now())
                        .user(userService.getUserById(trainingPlanDTO.getCreatorUserID()))
                        .build();

        trainingPlanRepo.save(trainingPlanModel);
        generateDays(trainingPlanModel);
    }

    // Eliminar un TrainingPlan por ID
    public void deleteTrainingPlanById(Long id) {
        deleteDays(id);
        trainingPlanRepo.deleteById(id);
    }

    private void generateDays(TrainingPlanModel trainingPlanModel){
        for (int i = 1; i < 8; i++){
            TrainingDayModel trainingDayModel =
                    TrainingDayModel
                            .builder()
                            .dayNumber(i)
                            .trainingPlan(trainingPlanModel)
                            .build();
            trainingPlanDayRepo.save(trainingDayModel);
        }
    }

    private void deleteDays(Long planID){
        trainingPlanDayRepo.deleteAll(trainingPlanDayRepo.findAllDaysByPlanId(planID));
    }

    public List<TrainingDayModel> addExerciseToPlan(Long planId, SaveExerciseToDayRoutineDTO exercise) {
        TrainingPlanModel planModel = trainingPlanRepo.findById(planId).orElseThrow(ResourceNotFoundException::new);

        return planModel.getDays();
    }
}