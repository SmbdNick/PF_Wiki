package ru.kolyan.pathfinder.controller.playerclass.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class CreatePlayerClassRequest {
    @NotEmpty(message = "Не пусто должно быть тута!!")
    private String name;
    private Integer hpPerLvl;
    private String description;
    @NotNull(message = "Не пусто должно быть тута!!")
    private UUID attributeComboId;
}
