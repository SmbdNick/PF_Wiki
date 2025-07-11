package ru.kolyan.pathfinder.controller.playerclass.request;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class AddClassMasteryRequest {
    private UUID masteryTierId;
    private String characteristic;
}
