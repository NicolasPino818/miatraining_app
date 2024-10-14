package com.inovisoft.backend_miatraining.repositories;

import com.inovisoft.backend_miatraining.models.ObjectiveModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IObjectiveRepo extends JpaRepository<ObjectiveModel,Long> {
    @Query("SELECT CASE WHEN COUNT(o) > 0 THEN TRUE ELSE FALSE END FROM ObjectiveModel o WHERE o.objectiveName = ?1")
    boolean existsByObjectiveName(String name);

}
