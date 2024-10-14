package com.inovisoft.backend_miatraining.security.config.data.userDetailsFormOptions;

import lombok.Getter;

@Getter
public enum BodyTypeEnum {
    SLIM("Delgado", "Cuerpo delgado, con una constitución ligera y baja masa muscular."),
    FIT("En forma", "Cuerpo atlético y musculoso, con un buen equilibrio entre masa muscular y grasa corporal baja."),
    OVERWEIGHT("Sobrepeso", "Cuerpo con exceso de peso debido a una acumulación de grasa corporal.");

    private final String name;
    private final String description;

    BodyTypeEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }
}


