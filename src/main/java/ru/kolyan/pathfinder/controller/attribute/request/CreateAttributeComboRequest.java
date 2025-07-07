package ru.kolyan.pathfinder.controller.attribute.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class CreateAttributeComboRequest {
    @NotEmpty(message = "АЙДИ атрибута ОБЯЗАТЕЛЬНО!")
    private UUID attributeId1;
    @NotEmpty(message = "АЙДИ атрибута ОБЯЗАТЕЛЬНО!")
    private UUID attributeId2;
    @NotEmpty(message = "Название комбо атрибутов ОБЯЗАТЕЛЬНО!")
    private String comboName;
}
