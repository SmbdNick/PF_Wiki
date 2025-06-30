package ru.kolyan.pathfinder.controller.attribute.request;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class UpdateByIdAttributeRequest {
    private String name;
}
