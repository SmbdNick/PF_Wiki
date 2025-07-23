package ru.kolyan.pathfinder.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kolyan.pathfinder.controller.lore.request.CreateLoreRequest;
import ru.kolyan.pathfinder.controller.lore.response.GetAllLoreResponse;
import ru.kolyan.pathfinder.controller.lore.response.GetByIdLoreResponse;
import ru.kolyan.pathfinder.model.Lore;

@Mapper(componentModel = "spring")
public interface LoreMapper {
    @Mapping(target = "id", ignore = true)
    Lore fromCreateDto(CreateLoreRequest request);

    GetByIdLoreResponse toGetByIdDto(Lore lore);

    GetAllLoreResponse.Lore toGetAllContentDto(Lore lore);
}
