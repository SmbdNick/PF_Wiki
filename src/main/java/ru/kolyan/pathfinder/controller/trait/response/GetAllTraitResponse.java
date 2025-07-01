package ru.kolyan.pathfinder.controller.trait.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class GetAllTraitResponse {
    List<Trait> content;

    @Getter
    @Builder
    @Jacksonized
    public static class Trait{
        private UUID id;
        private String name;
        private String description;
    }
}
