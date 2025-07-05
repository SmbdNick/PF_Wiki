package ru.kolyan.pathfinder.controller.language.request;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class UpdateByIdLanguageRequest {
    private String name;
}
