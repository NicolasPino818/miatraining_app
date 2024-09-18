package com.inovisoft.backend_miatraining.security;

import com.inovisoft.backend_miatraining.models.RoleModel;
import com.inovisoft.backend_miatraining.repositories.IRoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleConfiguration {

    @Bean
        //ESTE LINE RUNNER REVISA SI LOS ROLES EXISTEN O NO EN LA BASE DE DATOS
        //SI NO EXISTEN LOS CREA USANDO DE BASE EL ENUM
        //ESTO SE HACE AUTOMÁTICO AL INICIARSE LA APP
    CommandLineRunner commandLineRunner(IRoleRepo roleRepository){
        return args -> {
            for (RoleEnum roleEnum : RoleEnum.values()) {
                String roleName = roleEnum.name();
                if (!roleRepository.existsByRoleName(roleName)) {
                    RoleModel roleModel = RoleModel.builder().roleName(roleName).build();
                    roleRepository.save(roleModel);
                }
            }
        };
    }
}
