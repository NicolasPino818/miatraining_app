package com.inovisoft.backend_miatraining.security.config.data;

import com.inovisoft.backend_miatraining.models.TrainingTypeModel;
import com.inovisoft.backend_miatraining.repositories.ITrainingTypeRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TrainingTypeConfiguration {
    @Bean
    CommandLineRunner commandLineRunnerTrainingTypeConfiguration(ITrainingTypeRepo repo){
        return args -> {
            for (TrainingTypeEnum typeEnum : TrainingTypeEnum.values()) {
                String typeName = typeEnum.name();
                if (!repo.existsByTrainingTypeName(typeName)) {
                    TrainingTypeModel typeModel = TrainingTypeModel.builder().trainingTypeName(typeName).build();
                    repo.save(typeModel);
                }
            }
        };
    }
}
