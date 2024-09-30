package com.inovisoft.backend_miatraining.logic.services;

import com.inovisoft.backend_miatraining.errorHandlers.exceptions.ResourceNotFoundException;
import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.ExerciseCategoryDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.ExerciseCategoryPageResponseDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.ExerciseDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.ExercisePageResponseDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.mappers.ExerciseCategoryDTOMapper;
import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.mappers.ExerciseCategoryPageResponseDTOMapper;
import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.mappers.ExerciseDTOMapper;
import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.mappers.ExercisePageResponseDTOMapper;
import com.inovisoft.backend_miatraining.models.ExerciseCategoryModel;
import com.inovisoft.backend_miatraining.models.ExerciseModel;
import com.inovisoft.backend_miatraining.repositories.IExerciseCategoryRepo;
import com.inovisoft.backend_miatraining.repositories.IExerciseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {

    @Autowired
    IExerciseRepo exerciseRepo;
    @Autowired
    IExerciseCategoryRepo exerciseCategoryRepo;
    @Autowired
    ExerciseDTOMapper exerciseDTOMapper;
    @Autowired
    ExerciseCategoryDTOMapper exerciseCategoryDTOMapper;
    @Autowired
    ExercisePageResponseDTOMapper pageResponseDTOMapper;
    @Autowired
    ExerciseCategoryPageResponseDTOMapper categoryPageResponseDTOMapper;

    public ExercisePageResponseDTO getExercisesByPage(int page) {
        Pageable pageable = PageRequest.of(page, 20);
        Page<ExerciseDTO> dtoPage = exerciseRepo.findAll(pageable).map(exerciseDTOMapper);
        return pageResponseDTOMapper.apply(dtoPage);
    }

    public ExerciseDTO getExercise(Long id) {
        ExerciseModel model = exerciseRepo.findById(id).orElseThrow(ResourceNotFoundException::new);
        return exerciseDTOMapper.apply(model);
    }

    public void saveExercise(){

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

    public void saveExerciseCategory(){

    }

}
