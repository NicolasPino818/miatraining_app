package com.inovisoft.backend_miatraining.repositories;

import com.inovisoft.backend_miatraining.models.ExerciseModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExerciseRepo extends JpaRepository<ExerciseModel,Long> {
}
