package ru.kolyan.pathfinder.controller.background.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class CreateBackgroundRequest {
    @NotEmpty(message = "Не пусто должно быть тут длять!")
    private String name;
    private String description;
    @NotNull(message = "Не пусто должно быть тут длять!")
    private UUID skillFeatId;
    @NotNull(message = "Не пусто должно быть тут длять!")
    private UUID skillId;
    @NotNull(message = "Не пусто должно быть тут длять!")
    private UUID loreId;
    @NotNull(message = "Не пусто должно быть тут длять!")
    private UUID attributeComboId;
}
