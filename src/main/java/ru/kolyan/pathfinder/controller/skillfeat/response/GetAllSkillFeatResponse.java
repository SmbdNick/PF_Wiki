package ru.kolyan.pathfinder.controller.skillfeat.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class GetAllSkillFeatResponse {
    private List<SkillFeat> content;

    @Getter
    @Builder
    @Jacksonized
    public static class SkillFeat {
        private UUID id;
        private String name;
        private String description;
    }
}
