package ru.kolyan.pathfinder.controller.background.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class GetAllBackgroundResponse {
    List<Background> content;

    @Getter
    @Builder
    @Jacksonized
    public static class Background {
        private UUID id;
        private String name;
        private String description;
        private UUID skillFeatId;
        private String skillFeatName;
        private UUID skillId;
        private String skillName;
        private UUID loreId;
        private String loreName;
        private UUID attributeComboId;
        private String attributeComboName;
    }
}
