package com.inovisoft.backend_miatraining.security.config.data;

import com.inovisoft.backend_miatraining.models.RoleModel;
import com.inovisoft.backend_miatraining.repositories.IRoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleConfiguration {

    @Bean
    CommandLineRunner commandLineRunnerRoleConfiguration(IRoleRepo roleRepository){
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
