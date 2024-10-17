package com.inovisoft.backend_miatraining.logic.controllers;

import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.*;
import com.inovisoft.backend_miatraining.logic.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exercise")
public class ExerciseController {

    @Autowired
    ExerciseService exerciseService;

    @GetMapping()
    public ResponseEntity<ExercisePageResponseDTO> getExercises(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "100") int pageSize,
            @RequestParam(name = "search", required = false, defaultValue = "")  String search,
            @RequestParam(name = "category", required = false, defaultValue = "") String category,
            @RequestParam(name = "trainingType", required = false, defaultValue = "") String trainingType){
        ExercisePageResponseDTO exercises =
                exerciseService.getExercisesByPage(pageNumber, pageSize, search, category ,trainingType);
        return ResponseEntity.ok(exercises);
    }

    @GetMapping("/filters")
    public ResponseEntity<ExerciseFiltersDTO> getExerciseFilters(){
        return ResponseEntity.ok(exerciseService.getExerciseFilters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDTO> getExercise(@PathVariable("id") Long id){
        return ResponseEntity.ok(exerciseService.getExercise(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void saveExercise(@RequestBody ExerciseDTO exerciseDTO){
        exerciseService.saveExercise(exerciseDTO);
    }

    @PutMapping("/{exerciseID}")
    @ResponseStatus(HttpStatus.OK)
    public void updateExercise(@PathVariable("exerciseID") Long exerciseID, @RequestBody ExerciseDTO exerciseDTO){
        exerciseService.updateExercise(exerciseID,exerciseDTO);
    }

    @DeleteMapping("/{exerciseID}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteExercise(@PathVariable("exerciseID") Long exerciseID){
        exerciseService.deleteExercise(exerciseID);
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
    public void saveExerciseCategory(ExerciseCategoryDTO categoryDTO){
        exerciseService.saveExerciseCategory(categoryDTO);
    }

    @PutMapping("/category/{categoryID}")
    @ResponseStatus(HttpStatus.OK)
    public void updateExerciseCategory(@PathVariable("categoryID") Long categoryID,
                                       @RequestBody ExerciseCategoryDTO categoryDTO){
        exerciseService.updateExerciseCategory(categoryID,categoryDTO);
    }

    @GetMapping("/type")
    public ResponseEntity<List<TrainingTypeDTO>> getTrainingTypes(){
        return ResponseEntity.ok(exerciseService.getTrainingTypes());
    }

}
