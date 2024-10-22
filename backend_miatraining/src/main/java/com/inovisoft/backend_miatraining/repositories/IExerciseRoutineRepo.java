package com.inovisoft.backend_miatraining.repositories;

import com.inovisoft.backend_miatraining.models.ExerciseRoutineModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IExerciseRoutineRepo extends JpaRepository<ExerciseRoutineModel, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM ExerciseRoutineModel er WHERE er.exercise.exerciseID = :exerciseId")
    void deleteByExerciseId(@Param("exerciseId") Long exerciseId);
}
