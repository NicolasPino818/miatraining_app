package com.inovisoft.backend_miatraining.repositories;

import com.inovisoft.backend_miatraining.models.DietTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IDietTypeRepo extends JpaRepository<DietTypeModel,Long> {
    @Query("SELECT CASE WHEN COUNT(dt) > 0 THEN TRUE ELSE FALSE END FROM DietTypeModel dt WHERE dt.dietType = ?1")
    boolean existsByDietType(String name);

}
