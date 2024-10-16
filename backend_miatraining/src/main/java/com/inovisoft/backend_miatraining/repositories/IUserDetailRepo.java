package com.inovisoft.backend_miatraining.repositories;

import com.inovisoft.backend_miatraining.models.UserDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDetailRepo extends JpaRepository<UserDetailModel, Long> {

}

