package com.inovisoft.backend_miatraining.repositories;

import com.inovisoft.backend_miatraining.models.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface IUserRepo extends CrudRepository<UserModel, Long> {
    Optional<UserModel> findByEmailIgnoreCase(String email);

    @Query("SELECT u from UserModel u")
    ArrayList<UserModel> findAllUsers();

    @Transactional
    @Modifying
    @Query("UPDATE UserModel u SET u.password = ?2 WHERE u.email = ?1")
    void updatePasswordByEmail(String email, String password);
}
