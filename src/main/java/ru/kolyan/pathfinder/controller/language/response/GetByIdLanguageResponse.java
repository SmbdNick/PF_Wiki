package ru.kolyan.pathfinder.controller.language.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class GetByIdLanguageResponse {
    private UUID id;
    private String name;
}
