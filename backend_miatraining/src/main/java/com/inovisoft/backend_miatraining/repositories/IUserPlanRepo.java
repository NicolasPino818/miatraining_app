package com.inovisoft.backend_miatraining.repositories;

import com.inovisoft.backend_miatraining.models.UserPlanModel;
import com.inovisoft.backend_miatraining.repositories.compositeKeys.UsuarioPlanId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface IUserPlanRepo extends JpaRepository<UserPlanModel, UsuarioPlanId> {
    @Query("SELECT up FROM UserPlanModel up WHERE up.trainingPlan.planID = ?1")
    ArrayList<UserPlanModel> findAllUserPlansByPlanId(Long planID);

}