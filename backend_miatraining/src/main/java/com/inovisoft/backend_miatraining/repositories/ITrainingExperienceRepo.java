package com.inovisoft.backend_miatraining.repositories;

import com.inovisoft.backend_miatraining.models.ObjectiveModel;
import com.inovisoft.backend_miatraining.models.TrainingExperienceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrainingExperienceRepo extends JpaRepository<TrainingExperienceModel,Long> {
    @Query("SELECT CASE WHEN COUNT(te) > 0 THEN TRUE ELSE FALSE END FROM TrainingExperienceModel te WHERE te.experienceName = ?1")
    boolean existsByExperienceName(String name);

}
