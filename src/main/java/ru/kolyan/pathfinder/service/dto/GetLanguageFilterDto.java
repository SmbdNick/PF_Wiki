package ru.kolyan.pathfinder.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@Jacksonized
public class GetLanguageFilterDto {
    List<UUID> languageIdList;
}
