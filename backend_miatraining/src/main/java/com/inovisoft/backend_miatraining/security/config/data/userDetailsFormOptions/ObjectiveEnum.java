package com.inovisoft.backend_miatraining.security.config.data.userDetailsFormOptions;

import lombok.Getter;

@Getter
public enum ObjectiveEnum {
    LOSEWEIGHT("Perder Peso", "Ideal si deseas reducir grasa corporal o tonificar/definir."),
    GAINMUSCLE("Ganar Músculo", "Ideal si deseas aumentar tu masa muscular o subir de peso."),
    MAINTAINWEIGHT("Mantener Peso", "Ideal si deseas realizar un proceso de mantenimiento y permanecer en tu punto.");

    private final String name;
    private final String description;

    ObjectiveEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }
}


