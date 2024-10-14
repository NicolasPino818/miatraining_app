package com.inovisoft.backend_miatraining.security.config.data.userDetailsFormOptions;

import com.inovisoft.backend_miatraining.models.*;
import com.inovisoft.backend_miatraining.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserDetailsFormConfiguration {

    @Bean
    CommandLineRunner commandLineRunnerUserDetailsConfiguration(ITrainingTypeRepo trainingTypeRepo,
                                                                IBodyTypeRepo bodyTypeRepo,
                                                                IObjectiveRepo objectiveRepo,
                                                                IDietTypeRepo dietTypeRepo,
                                                                ITrainingExperienceRepo trainingExperienceRepo){
        return args -> {
            for (TrainingTypeEnum typeEnum : TrainingTypeEnum.values()) {
                String type = typeEnum.name();
                if (!trainingTypeRepo.existsByTrainingTypeName(type)) {
                    TrainingTypeModel model = TrainingTypeModel.builder().trainingTypeName(type).build();
                    trainingTypeRepo.save(model);
                }
            }
            for (BodyTypeEnum bodyTypeEnum : BodyTypeEnum.values()) {
                String bodyTypeName = bodyTypeEnum.getName();
                String bodyTypeDescription = bodyTypeEnum.getDescription();

                if (!bodyTypeRepo.existsByBodyTypeName(bodyTypeName)) {
                    BodyTypeModel model = BodyTypeModel.builder()
                            .bodyTypeName(bodyTypeName)
                            .description(bodyTypeDescription)
                            .build();
                    bodyTypeRepo.save(model);
                }
            }
            for (ObjectiveEnum objectiveEnum : ObjectiveEnum.values()) {
                String objectiveTypeName = objectiveEnum.getName();
                String objectiveTypeDescription = objectiveEnum.getDescription();

                if (!objectiveRepo.existsByObjectiveName(objectiveTypeName)) {
                    ObjectiveModel model = ObjectiveModel.builder()
                            .objectiveName(objectiveTypeName)
                            .description(objectiveTypeDescription)
                            .build();
                    objectiveRepo.save(model);
                }
            }
            for (DietTypeEnum dietTypeEnum : DietTypeEnum.values()) {
                String dietType = dietTypeEnum.getDietType();

                if (!dietTypeRepo.existsByDietType(dietType)) {
                    DietTypeModel model = DietTypeModel.builder()
                            .dietType(dietType)
                            .build();
                    dietTypeRepo.save(model);
                }
            }
            for (TrainingExperienceEnum trainingExperienceEnum : TrainingExperienceEnum.values()) {
                String name = trainingExperienceEnum.getName();
                String description = trainingExperienceEnum.getDescription();
                if (!trainingExperienceRepo.existsByExperienceName(name)) {
                    TrainingExperienceModel model = TrainingExperienceModel.builder()
                            .experienceName(name)
                            .description(description)
                            .build();
                    trainingExperienceRepo.save(model);
                }
            }
        };
    }
}
