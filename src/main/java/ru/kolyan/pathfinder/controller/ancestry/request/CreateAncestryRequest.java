package ru.kolyan.pathfinder.controller.ancestry.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class CreateAncestryRequest {
    @NotEmpty(message = "Название Родословной ОБЯЗАТЕЛЬНО!!!")
    private String name;
    private Integer hp;
    private String size;
    private Integer speed;
    @NotNull(message = "Айди Комбо Атрибутов ОБЯЗАТЕЛЬНО!!!")
    private UUID attributeComboId;
    private String description;
}
