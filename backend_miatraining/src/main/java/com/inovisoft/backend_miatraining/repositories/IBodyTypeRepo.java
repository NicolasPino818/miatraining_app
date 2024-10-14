package com.inovisoft.backend_miatraining.repositories;

import com.inovisoft.backend_miatraining.models.BodyTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBodyTypeRepo extends JpaRepository<BodyTypeModel,Long> {
    @Query("SELECT CASE WHEN COUNT(bt) > 0 THEN TRUE ELSE FALSE END FROM BodyTypeModel bt WHERE bt.bodyTypeName = ?1")
    boolean existsByBodyTypeName(String name);

}
