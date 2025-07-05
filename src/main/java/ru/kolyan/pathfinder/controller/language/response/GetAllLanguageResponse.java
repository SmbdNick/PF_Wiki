package ru.kolyan.pathfinder.controller.language.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class GetAllLanguageResponse {
    List<Language> content;

    @Getter
    @Builder
    @Jacksonized
    public static class Language {
        private UUID id;
        private String name;
    }
}
