package com.inovisoft.backend_miatraining.repositories;

import com.inovisoft.backend_miatraining.models.ExerciseCategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExerciseCategoryRepo extends JpaRepository<ExerciseCategoryModel,Long> {
}
