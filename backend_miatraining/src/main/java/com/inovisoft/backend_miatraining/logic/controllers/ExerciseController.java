package com.inovisoft.backend_miatraining.logic.controllers;

import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.ExerciseCategoryDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.ExerciseCategoryPageResponseDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.ExerciseDTO;
import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.ExercisePageResponseDTO;
import com.inovisoft.backend_miatraining.logic.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/exercise")
public class ExerciseController {

    @Autowired
    ExerciseService exerciseService;

    @GetMapping()
    public ResponseEntity<ExercisePageResponseDTO> getExercises(
            @RequestParam(name = "page", defaultValue = "0") int page) {
        return ResponseEntity.ok(exerciseService.getExercisesByPage(page));
    }

    @GetMapping("{id}")
    public ResponseEntity<ExerciseDTO> getExercise(@PathVariable("id") Long id){
        return ResponseEntity.ok(exerciseService.getExercise(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void saveExercise(){

    }

    @GetMapping("/category")
    public ResponseEntity<ExerciseCategoryPageResponseDTO> getExerciseCategories(
            @RequestParam(name = "page", defaultValue = "0") int page){
        return ResponseEntity.ok(exerciseService.getExerciseCategoriesByPage(page));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<ExerciseCategoryDTO> getExerciseCategory(@PathVariable("id") Long id){
        return ResponseEntity.ok(exerciseService.getExerciseCategory(id));
    }

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveExerciseCategory(){

    }

}
