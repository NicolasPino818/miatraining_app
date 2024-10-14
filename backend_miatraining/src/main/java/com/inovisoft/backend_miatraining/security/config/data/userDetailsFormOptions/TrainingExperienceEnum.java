package com.inovisoft.backend_miatraining.security.config.data.userDetailsFormOptions;

import lombok.Getter;

@Getter
public enum TrainingExperienceEnum {
    BASIC("Básico", "Nunca has entrenado en el gimnasio o llevas menos de 6 meses."),
    INTERMEDIATE("Intermedio", "Tienes experiencia en el gimnasio y conoces los ejercicios básicos."),
    ADVANCED("Avanzado", "Eres deportista de alto rendimiento.");

    private final String name;
    private final String description;

    TrainingExperienceEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }
}


