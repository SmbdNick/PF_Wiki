package ru.kolyan.pathfinder.controller.playerclass.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class GetAllPlayerClassResponse {
    private List<PlayerClass> content;

    @Getter
    @Builder
    @Jacksonized
    public static class PlayerClass {
        private UUID id;
        private String name;
        private Integer hpPerLvl;
        private String description;
        private UUID attributeComboId;
        private String attributeComboName;
    }
}
