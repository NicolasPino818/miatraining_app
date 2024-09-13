package com.inovisoft.backend_miatraining.repositories;

import com.inovisoft.backend_miatraining.models.RoleModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface IRoleRepo extends CrudRepository<RoleModel, Long> {
    @Query("SELECT r FROM RoleModel r")
    ArrayList<RoleModel> getAllRoles();
    @Query("SELECT r FROM RoleModel r WHERE roleName = ?1")
    Optional<RoleModel> findRoleByRoleNameIgnoreCase(String role_name);
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END FROM RoleModel r WHERE r.roleName = ?1")
    boolean existsByRoleName(String role_name);
}
