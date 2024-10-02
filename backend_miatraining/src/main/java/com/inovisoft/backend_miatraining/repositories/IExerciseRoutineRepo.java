package com.inovisoft.backend_miatraining.repositories;

import com.inovisoft.backend_miatraining.models.ExerciseRoutineModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExerciseRoutineRepo extends JpaRepository<ExerciseRoutineModel, Long> {

}
