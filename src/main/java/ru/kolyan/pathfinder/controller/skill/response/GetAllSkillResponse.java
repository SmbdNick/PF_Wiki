package ru.kolyan.pathfinder.controller.skill.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class GetAllSkillResponse {
    private List<Skill> content;

    @Getter
    @Builder
    @Jacksonized
    public static class Skill {
        private UUID id;
        private String name;
    }
}
