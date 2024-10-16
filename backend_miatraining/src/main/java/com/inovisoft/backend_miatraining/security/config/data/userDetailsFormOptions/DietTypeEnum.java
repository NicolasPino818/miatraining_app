package com.inovisoft.backend_miatraining.security.config.data.userDetailsFormOptions;

import lombok.Getter;

@Getter
public enum DietTypeEnum {
    NORMAL("Estándar"),
    VEGETARIAN("Vagetariana"),
    VEGAN("Vegana"),
    PESCETARIAN("Pescetariana");

    private final String dietType;

    DietTypeEnum(String dietType) {
        this.dietType = dietType;
    }
}


