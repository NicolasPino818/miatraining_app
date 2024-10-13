package com.inovisoft.backend_miatraining.logic.services;

import com.inovisoft.backend_miatraining.errorHandlers.exceptions.ResourceNotFoundException;
import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.*;
import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.mappers.*;
import com.inovisoft.backend_miatraining.models.ExerciseCategoryModel;
import com.inovisoft.backend_miatraining.models.ExerciseModel;
import com.inovisoft.backend_miatraining.repositories.IExerciseCategoryRepo;
import com.inovisoft.backend_miatraining.repositories.IExerciseRepo;
import com.inovisoft.backend_miatraining.repositories.ITrainingTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    @Autowired
    IExerciseRepo exerciseRepo;
    @Autowired
    ITrainingTypeRepo trainingTypeRepo;
    @Autowired
    IExerciseCategoryRepo exerciseCategoryRepo;
    @Autowired
    TrainingTypeDTOMapper trainingTypeDTOMapper;
    @Autowired
    ExerciseDTOMapper exerciseDTOMapper;
    @Autowired
    ExerciseCategoryDTOMapper exerciseCategoryDTOMapper;
    @Autowired
    ExercisePageResponseDTOMapper pageResponseDTOMapper;
    @Autowired
    ExerciseCategoryPageResponseDTOMapper categoryPageResponseDTOMapper;

    public ExercisePageResponseDTO getExercisesByPage(int page) {
        Pageable pageable = PageRequest.of(page, 100);
        Page<ExerciseDTO> dtoPage = exerciseRepo.findAll(pageable).map(exerciseDTOMapper);
        return pageResponseDTOMapper.apply(dtoPage);
    }

    public ExerciseDTO getExercise(Long id) {
        ExerciseModel model = exerciseRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
        return exerciseDTOMapper.apply(model);
    }

    public void saveExercise(ExerciseDTO exerciseDTO){
        ExerciseModel model = ExerciseModel
                .builder()
                .exerciseID(null)
                .exerciseName(exerciseDTO.getName())
                .imageLink(exerciseDTO.getImageSrc())
                .tutorialLink(exerciseDTO.getTutorialSrc())
                .trainingType(trainingTypeRepo.findByTrainingTypeName(
                        exerciseDTO.getTrainingType())
                        .orElseThrow(ResourceNotFoundException::new))
                .description(exerciseDTO.getDescription())
                .build();
        if(exerciseDTO.getExerciseCategories() != null && !exerciseDTO.getExerciseCategories().isEmpty()){
            model.setCategories(exerciseDTO.getExerciseCategories()
                    .stream().map((dto)-> ExerciseCategoryModel
                            .builder()
                            .exerciseCategoryID(dto.getId())
                            .categoryName(dto.getCategoryName())
                            .build()).toList());
        }
        exerciseRepo.save(model);
    }

    public void updateExercise(Long id, ExerciseDTO exerciseDTO){
        ExerciseModel model = exerciseRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
        model.setExerciseName(exerciseDTO.getName());
        model.setImageLink(exerciseDTO.getImageSrc());
        model.setTutorialLink(exerciseDTO.getTutorialSrc());
        model.setTrainingType(trainingTypeRepo.findByTrainingTypeName(
                                exerciseDTO.getTrainingType())
                        .orElseThrow(ResourceNotFoundException::new));
        model.setDescription(exerciseDTO.getDescription());
        if(exerciseDTO.getExerciseCategories() != null && !exerciseDTO.getExerciseCategories().isEmpty()){
            model.setCategories(exerciseDTO.getExerciseCategories()
                    .stream().map((dto)-> ExerciseCategoryModel
                            .builder()
                            .exerciseCategoryID(dto.getId())
                            .categoryName(dto.getCategoryName())
                            .build()).toList());
        }
        exerciseRepo.save(model);
    }

    public void deleteExercise(Long exerciseID) {
        exerciseRepo.deleteById(exerciseID);
    }

    public ExerciseCategoryPageResponseDTO getExerciseCategoriesByPage(int page) {
        Pageable pageable = PageRequest.of(page, 20);
        Page<ExerciseCategoryDTO> dtoPage = exerciseCategoryRepo.findAll(pageable).map(exerciseCategoryDTOMapper);
        return categoryPageResponseDTOMapper.apply(dtoPage);
    }

    public ExerciseCategoryDTO getExerciseCategory(Long id) {
        ExerciseCategoryModel model = exerciseCategoryRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
        return exerciseCategoryDTOMapper.apply(model);
    }

    public void saveExerciseCategory(ExerciseCategoryDTO categoryDTO){
        ExerciseCategoryModel categoryModel =
                ExerciseCategoryModel
                        .builder()
                        .exerciseCategoryID(null)
                        .categoryName(categoryDTO.getCategoryName())
                        .build();
        exerciseCategoryRepo.save(categoryModel);
    }

    public void updateExerciseCategory(Long id, ExerciseCategoryDTO categoryDTO){
        ExerciseCategoryModel categoryModel = exerciseCategoryRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
        categoryModel.setCategoryName(categoryDTO.getCategoryName());
        exerciseCategoryRepo.save(categoryModel);
    }

    public List<TrainingTypeDTO> getTrainingTypes() {
        return trainingTypeRepo.findAll().stream().map(trainingTypeDTOMapper).toList();
    }


}
