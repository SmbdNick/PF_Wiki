package ru.kolyan.pathfinder.controller.attribute.response;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class GetByIdAttributeResponse {
    private UUID id;
    private String name;
}
