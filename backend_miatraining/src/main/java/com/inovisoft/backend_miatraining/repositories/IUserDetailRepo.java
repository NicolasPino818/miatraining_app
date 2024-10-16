package com.inovisoft.backend_miatraining.repositories;

import com.inovisoft.backend_miatraining.models.UserModel;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface IUserRepo extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByEmailIgnoreCase(String email);

    @Query("SELECT u FROM UserModel u")
    ArrayList<UserModel> findAllUsers();

    @Transactional
    @Modifying
    @Query("UPDATE UserModel u SET u.password = ?2 WHERE u.email = ?1")
    void updatePasswordByEmail(String email, String password);

    @Query("SELECT u FROM UserModel u WHERE " +
            "(:searchTerm IS NULL OR :searchTerm = '' OR " +
            "(CAST(u.userID AS string) LIKE CONCAT('%', :searchTerm, '%') " + // Búsqueda por ID
            "OR LOWER(u.email) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(u.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(u.surname) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
            "OR LOWER(CONCAT(u.name, ' ', u.surname)) LIKE LOWER(CONCAT('%', :searchTerm, '%')))) " + // Búsqueda por name + surname
            "AND (:role IS NULL OR :role = '' OR LOWER(u.role.roleName) = LOWER(:role))")
    Page<UserModel> findUsersByEmailOrNameOrLastNameAndRole(@Param("searchTerm") String searchTerm,
                                                            @Param("role") String role, Pageable pageable);






}

