package ru.kolyan.pathfinder.mapper;

import org.springframework.stereotype.Component;
import ru.kolyan.pathfinder.controller.lore.request.CreateLoreRequest;
import ru.kolyan.pathfinder.controller.lore.response.GetAllLoreResponse;
import ru.kolyan.pathfinder.controller.lore.response.GetByIdLoreResponse;
import ru.kolyan.pathfinder.model.Lore;

@Component
public class LoreMapper {
    public Lore fromCreateDto(CreateLoreRequest request) {
        Lore lore = new Lore();
        lore.setName(request.getName());

        return lore;
    }

    public GetByIdLoreResponse toGetByIdDto(Lore lore) {
        return GetByIdLoreResponse.builder()
                .id(lore.getId())
                .name(lore.getName())
                .build();
    }

    public GetAllLoreResponse.Lore toGetAllContentDto(Lore lore) {
        return GetAllLoreResponse.Lore.builder()
                .id(lore.getId())
                .name(lore.getName())
                .build();
    }
}
