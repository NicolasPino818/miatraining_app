package com.inovisoft.backend_miatraining.logic.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inovisoft.backend_miatraining.logic.DTOs.exerciseDTO.*;
import com.inovisoft.backend_miatraining.logic.services.ExerciseService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/exercise")
public class ExerciseController {

    @Autowired
    ExerciseService exerciseService;

    @GetMapping()
    public ResponseEntity<ExercisePageResponseDTO> getExercises(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "50") int pageSize,
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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveExercise(@RequestParam("name") String name,
                             @RequestParam(value = "tutorialSrc", required = false) String tutorialSrc,
                             @RequestParam("imageFile") MultipartFile imageFile,
                             @RequestParam(value = "description", required = false) String description,
                             @RequestParam("trainingType") String trainingType,
                             @RequestParam(value = "exerciseCategories", required = false) String exerciseCategoriesJson) throws IOException {

        List<ExerciseCategoryDTO> exerciseCategories = null;
        if(exerciseCategoriesJson != null){
            ObjectMapper objectMapper = new ObjectMapper();
            exerciseCategories = objectMapper.readValue(exerciseCategoriesJson, new TypeReference<List<ExerciseCategoryDTO>>() {});
        }
        if(exerciseCategories != null && exerciseCategories.isEmpty()) exerciseCategories = null;

        ExerciseSubmitDTO exerciseDTO = ExerciseSubmitDTO
                .builder()
                .name(name)
                .imageFile(imageFile)
                .description(description)
                .trainingType(trainingType)
                .tutorialSrc(tutorialSrc)
                .exerciseCategories(exerciseCategories)
                .build();
        exerciseService.saveExercise(exerciseDTO);
    }

    @PutMapping("/{exerciseID}")
    @ResponseStatus(HttpStatus.OK)
    public void updateExercise(@PathVariable("exerciseID") Long exerciseID,
                               @RequestParam("name") String name,
                               @RequestParam(value = "tutorialSrc", required = false) String tutorialSrc,
                               @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
                               @RequestParam(value = "description", required = false) String description,
                               @RequestParam("trainingType") String trainingType,
                               @RequestParam(value = "exerciseCategories", required = false) String exerciseCategoriesJson) throws IOException{

        List<ExerciseCategoryDTO> exerciseCategories = null;
        if(exerciseCategoriesJson != null){
            ObjectMapper objectMapper = new ObjectMapper();
            exerciseCategories = objectMapper.readValue(exerciseCategoriesJson, new TypeReference<List<ExerciseCategoryDTO>>() {});
        }
        if(exerciseCategories != null && exerciseCategories.isEmpty()) exerciseCategories = null;

        ExerciseSubmitDTO exerciseDTO = ExerciseSubmitDTO
                .builder()
                .name(name)
                .description(description)
                .trainingType(trainingType)
                .tutorialSrc(tutorialSrc)
                .exerciseCategories(exerciseCategories)
                .build();

        if(imageFile != null) exerciseDTO.setImageFile(imageFile);

        exerciseService.updateExercise(exerciseID, exerciseDTO);
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
