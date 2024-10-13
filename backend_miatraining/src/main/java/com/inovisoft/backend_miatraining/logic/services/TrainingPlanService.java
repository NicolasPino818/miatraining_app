package com.inovisoft.backend_miatraining.logic.services;

import com.inovisoft.backend_miatraining.errorHandlers.exceptions.ResourceNotFoundException;
import com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.SaveExerciseToDayRoutineDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.SaveTrainingPlanDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.SaveUsersToPlanDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.mappers.TrainingPlanResponseDTOMapper;
import com.inovisoft.backend_miatraining.logic.DTOs.trainingPlanDTO.response.TrainingPlanResponseDTO;
import com.inovisoft.backend_miatraining.models.*;
import com.inovisoft.backend_miatraining.repositories.*;
import com.inovisoft.backend_miatraining.repositories.compositeKeys.UsuarioPlanId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class TrainingPlanService {

    @Autowired
    ITrainingPlanRepo trainingPlanRepo;
    @Autowired
    ITrainingDayRepo trainingDayRepo;
    @Autowired
    IUserRepo userRepo;
    @Autowired
    IUserPlanRepo userPlanRepo;
    @Autowired
    IExerciseRepo exerciseRepo;
    @Autowired
    IExerciseRoutineRepo exerciseRoutineRepo;
    @Autowired
    UserService userService;
    @Autowired
    TrainingPlanResponseDTOMapper trainingPlanResponseDTOMapper;

    // Obtener un TrainingPlan por ID
    public TrainingPlanResponseDTO getTrainingPlanById(Long id) {
        TrainingPlanModel planModel = trainingPlanRepo.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return trainingPlanResponseDTOMapper.apply(planModel);
    }

    // Obtener un TrainingPlan por Email
    public TrainingPlanResponseDTO getTrainingPlanByEmail(String email) {
        Optional<TrainingPlanModel> planModel = trainingPlanRepo.findByUserEmail(email);
        return planModel.map(trainingPlanModel ->
                trainingPlanResponseDTOMapper.apply(trainingPlanModel)).orElse(null);
    }

    // Obtener todos los TrainingPlans
    public ArrayList<TrainingPlanResponseDTO> getAllTrainingPlans() {
        Iterable<TrainingPlanModel> planModels = trainingPlanRepo.findAll();
        ArrayList<TrainingPlanResponseDTO> planResponseDTOS = new ArrayList<>();
        planModels.forEach(model->{
            planResponseDTOS.add(trainingPlanResponseDTOMapper.apply(model));
        });
        return planResponseDTOS;
    }

    // Editar TrainingPlan por ID
    public void updateExerciseRoutine(Long routineID, ExerciseRoutineModel updatedRoutine) {
        // Buscar la rutina de ejercicio por ID
        ExerciseRoutineModel existingRoutine = exerciseRoutineRepo.findById(routineID)
                .orElseThrow(ResourceNotFoundException::new);

        // Actualizar los campos necesarios de la rutina
        existingRoutine.setSeries(updatedRoutine.getSeries());
        existingRoutine.setRepetitions(updatedRoutine.getRepetitions());
        existingRoutine.setRestMinutes(updatedRoutine.getRestMinutes());

        // Si es necesario actualizar el ejercicio, también se puede hacer aquí:
        existingRoutine.setExercise(updatedRoutine.getExercise());

        // Guardar los cambios en la base de datos
        exerciseRoutineRepo.save(existingRoutine);
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
        deleteUserPlansByPlanID(id);
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
            trainingDayRepo.save(trainingDayModel);
        }
    }

    private void deleteDays(Long planID){
        trainingDayRepo.deleteAll(trainingDayRepo.findAllDaysByPlanId(planID));
    }

    public void addUsersToPlan(Long planID,SaveUsersToPlanDTO dto){
        //Obtener el plan model de la db
        TrainingPlanModel planModel = trainingPlanRepo.findById(planID).orElseThrow(ResourceNotFoundException::new);
        //Obtener la lista de userModels de la db
        ArrayList<UserModel> userModels = new ArrayList<>(userRepo.findAllById(dto.getUserID()));
        //Por cada usuario añadido al plan se crea una instancia de UserPlanModel y ser guarda en la db
        userModels.forEach(userModel -> {
            //Si la lista de training plan para ese usuario está vacía
            if(userModel.getUserPlan().isEmpty()){
                //Se crea un nuevo id para el UserPlanModel
                UsuarioPlanId usuarioPlanId = new UsuarioPlanId(userModel.getUserID(), planModel.getPlanID());
                //Se crea el nuevo UserPlanModel
                UserPlanModel userPlanModel = new UserPlanModel(usuarioPlanId, LocalDate.now(), userModel, planModel);
                //Se guarda el plan del usuario
                userPlanRepo.save(userPlanModel);
            }
        });
    }

    public void deleteUserPlansByPlanID(Long planID){
        userPlanRepo.deleteAll(userPlanRepo.findAllUserPlansByPlanId(planID));
    }

    public void deleteUsersFromPlan(Long planID,SaveUsersToPlanDTO dto){
        //Obtener el plan model de la db
        TrainingPlanModel planModel = trainingPlanRepo.findById(planID).orElseThrow(ResourceNotFoundException::new);
        //Obtener la lista de userModels de la db
        ArrayList<UserModel> userModels = new ArrayList<>(userRepo.findAllById(dto.getUserID()));
        //Por cada usuario añadido al plan se crea una instancia de UserPlanModel y ser guarda en la db
        userModels.forEach(userModel -> {
            //Si el usuario tiene un plan se elimina
            if(!userModel.getUserPlan().isEmpty()){
                //Se guarda el plan del usuario
                userPlanRepo.deleteById(new UsuarioPlanId(userModel.getUserID(), planModel.getPlanID()));
            }
        });
    }

    public void addRoutineToPlan(Long planId, SaveExerciseToDayRoutineDTO exerciseRoutine) {
        ExerciseModel exerciseModel = exerciseRepo.findById(exerciseRoutine.getExerciseID())
                .orElseThrow(ResourceNotFoundException::new);
        TrainingDayModel dayModel = trainingDayRepo.findByDayNumberAndPlanId(exerciseRoutine.getDayNumber(),planId)
                .orElseThrow(ResourceNotFoundException::new);

        ExerciseRoutineModel routineModel =
                ExerciseRoutineModel
                        .builder()
                        .exercise(exerciseModel)
                        .trainingDay(dayModel)
                        .repetitions(exerciseRoutine.getRepetitions())
                        .series(exerciseRoutine.getSeries())
                        .restMinutes(exerciseRoutine.getRestMinutes())
                        .build();

        exerciseRoutineRepo.save(routineModel);
    }


    public void deleteRoutine(Long routineID){
        exerciseRoutineRepo.deleteById(routineID);
    }

}