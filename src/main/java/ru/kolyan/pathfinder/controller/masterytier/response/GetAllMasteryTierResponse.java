package ru.kolyan.pathfinder.controller.masterytier.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class GetAllMasteryTierResponse {
    List<MasteryTier> content;

    @Getter
    @Builder
    @Jacksonized
    public static class MasteryTier{
        private UUID id;
        private String name;
    }
}
