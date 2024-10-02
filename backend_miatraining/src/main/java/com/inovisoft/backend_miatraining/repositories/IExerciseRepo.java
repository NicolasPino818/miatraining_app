package com.inovisoft.backend_miatraining.repositories;

import com.inovisoft.backend_miatraining.models.ExerciseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExerciseRepo extends JpaRepository<ExerciseModel,Long> {
}
