package com.inovisoft.backend_miatraining.logic.services;

import com.inovisoft.backend_miatraining.errorHandlers.exceptions.ResourceNotFoundException;
import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.*;
import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.mappers.*;
import com.inovisoft.backend_miatraining.models.ExerciseCategoryModel;
import com.inovisoft.backend_miatraining.models.ExerciseModel;
import com.inovisoft.backend_miatraining.repositories.IExerciseCategoryRepo;
import com.inovisoft.backend_miatraining.repositories.IExerciseRepo;
import com.inovisoft.backend_miatraining.repositories.IExerciseRoutineRepo;
import com.inovisoft.backend_miatraining.repositories.ITrainingTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    @Autowired
    GoogleCloudStorageService googleCloudStorageService;
    @Autowired
    IExerciseRoutineRepo exerciseRoutineRepo;

    public ExercisePageResponseDTO getExercisesByPage(int pageNumber,
                                                      int pageSize,
                                                      String search,
                                                      String category,
                                                      String trainingType) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        String searchTerm = (search == null || search.trim().isEmpty()) ? "" : search.trim();
        String categoryFilter = (category == null || category.trim().isEmpty()) ? "" : category.trim();
        String trainingTypeFilter = (trainingType == null || trainingType.trim().isEmpty()) ? "" : trainingType.trim();

        Page<ExerciseModel> exercisePage = exerciseRepo.findExercisesWithFilters(
                searchTerm, categoryFilter, trainingTypeFilter, pageable);

        Page<ExerciseDTO> dtoPage = exercisePage.map(exerciseDTOMapper);

        return pageResponseDTOMapper.apply(dtoPage);
    }


    public ExerciseDTO getExercise(Long id) {
        ExerciseModel model = exerciseRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
        return exerciseDTOMapper.apply(model);
    }

    public void saveExercise(ExerciseSubmitDTO exerciseDTO) throws IOException {
        ExerciseModel model = ExerciseModel
                .builder()
                .exerciseID(null)
                .exerciseName(exerciseDTO.getName())
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
        model = exerciseRepo.save(model);
        try{
            model.setImageLink(googleCloudStorageService.uploadImagenEjercicio(exerciseDTO.getImageFile(), model.getExerciseID()));
            exerciseRepo.save(model);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void updateExercise(Long id, ExerciseSubmitDTO exerciseDTO) throws IOException {
        ExerciseModel model = exerciseRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
        model.setExerciseName(exerciseDTO.getName());

        if(exerciseDTO.getImageFile() != null)
            model.setImageLink(googleCloudStorageService.uploadImagenEjercicio(exerciseDTO.getImageFile(),id));

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
        exerciseRoutineRepo.deleteByExerciseId(exerciseID);
        ExerciseModel model = exerciseRepo.findById(exerciseID).orElseThrow(ResourceNotFoundException::new);
        if(model.getImageLink() != null) googleCloudStorageService.deleteResourceByPath(model.getImageLink());
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


    public ExerciseFiltersDTO getExerciseFilters() {
        List<ExerciseCategoryDTO> categoryDTOS =
                exerciseCategoryRepo.findAll().stream().map(exerciseCategoryDTOMapper).toList();
        List<TrainingTypeDTO> trainingTypeDTOS =
                trainingTypeRepo.findAll().stream().map(trainingTypeDTOMapper).toList();
        return ExerciseFiltersDTO
                .builder()
                .categories(categoryDTOS)
                .trainingTypes(trainingTypeDTOS)
                .build();
    }
}
