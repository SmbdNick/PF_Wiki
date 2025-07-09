package ru.kolyan.pathfinder.controller.lore.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class GetAllLoreResponse {
    private List<Lore> content;

    @Getter
    @Builder
    @Jacksonized
    public static class Lore {
        private UUID id;
        private String name;
    }
}
