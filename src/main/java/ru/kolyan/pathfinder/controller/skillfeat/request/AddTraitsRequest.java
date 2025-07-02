package ru.kolyan.pathfinder.controller.skillfeat.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class AddTraitsRequest {
    @NotEmpty(message = "Не пусто должно быть здесь, длять!")
    List<UUID> traitIdList;
}
