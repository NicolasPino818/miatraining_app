package com.inovisoft.backend_miatraining.repositories;

import com.inovisoft.backend_miatraining.models.ForgotPasswordModel;
import com.inovisoft.backend_miatraining.models.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IForgotPasswordRepo extends CrudRepository<ForgotPasswordModel,Long> {
    @Query("SELECT fp FROM ForgotPasswordModel fp WHERE fp.otp = ?1 AND fp.user = ?2")
    Optional<ForgotPasswordModel> findByOtpAndUser(Integer otp, UserModel user);
}