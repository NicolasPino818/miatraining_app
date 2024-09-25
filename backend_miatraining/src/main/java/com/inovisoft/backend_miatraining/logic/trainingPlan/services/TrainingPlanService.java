package com.inovisoft.backend_miatraining.logic.trainingPlan.services;

import com.inovisoft.backend_miatraining.errorHandlers.exceptions.ResourceNotFoundException;
import com.inovisoft.backend_miatraining.logic.trainingPlan.DTO.SaveTrainingPlanDTO;
import com.inovisoft.backend_miatraining.logic.user.services.UserService;
import com.inovisoft.backend_miatraining.models.TrainingDayModel;
import com.inovisoft.backend_miatraining.models.TrainingPlanModel;
import com.inovisoft.backend_miatraining.repositories.IRoleRepo;
import com.inovisoft.backend_miatraining.repositories.ITrainingDayRepo;
import com.inovisoft.backend_miatraining.repositories.ITrainingPlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TrainingPlanService {

    @Autowired
    ITrainingPlanRepo ITrainingPlanRepo;
    @Autowired
    ITrainingDayRepo trainingPlanDayRepo;
    @Autowired
    UserService userService;

    // Obtener un TrainingPlan por ID
    public TrainingPlanModel getTrainingPlanById(Long id) {
        return ITrainingPlanRepo.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    // Obtener todos los TrainingPlans
    public Iterable<TrainingPlanModel> getAllTrainingPlans() {
        return ITrainingPlanRepo.findAll();
    }

    // Crear o actualizar un TrainingPlan
    public void saveTrainingPlan(SaveTrainingPlanDTO trainingPlanDTO) {

        TrainingPlanModel trainingPlanModel =
                TrainingPlanModel
                        .builder()
                        .creationDate(LocalDate.now())
                        .user(userService.getUserById(trainingPlanDTO.getCreatorUserID()))
                        .build();

        ITrainingPlanRepo.save(trainingPlanModel);
        generateDays(trainingPlanModel);
    }

    // Eliminar un TrainingPlan por ID
    public void deleteTrainingPlanById(Long id) {
        deleteDays(id);
        ITrainingPlanRepo.deleteById(id);
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

}