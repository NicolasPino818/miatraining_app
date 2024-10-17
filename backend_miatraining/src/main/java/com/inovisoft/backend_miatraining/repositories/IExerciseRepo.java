package com.inovisoft.backend_miatraining.repositories;

import com.inovisoft.backend_miatraining.models.ExerciseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IExerciseRepo extends JpaRepository<ExerciseModel, Long> {

    @Query("SELECT DISTINCT e FROM ExerciseModel e " +
            "LEFT JOIN e.categories c " +  // Relación con las categorías
            "LEFT JOIN e.trainingType t " + // Relación con el tipo de entrenamiento
            "WHERE " +
            "(:searchTerm IS NULL OR :searchTerm = '' OR " +
            "LOWER(e.exerciseName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) " + // Búsqueda solo por nombre de ejercicio
            "AND (:category IS NULL OR :category = '' OR LOWER(c.categoryName) = LOWER(:category)) " + // Filtro por categoría
            "AND (:trainingType IS NULL OR :trainingType = '' OR LOWER(t.trainingTypeName) = LOWER(:trainingType))") // Filtro por tipo de entrenamiento
    Page<ExerciseModel> findExercisesWithFilters(@Param("searchTerm") String searchTerm,
                                                 @Param("category") String category,
                                                 @Param("trainingType") String trainingType,
                                                 Pageable pageable);
}




