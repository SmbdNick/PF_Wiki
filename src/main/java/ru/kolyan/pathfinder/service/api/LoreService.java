package ru.kolyan.pathfinder.service.api;

import ru.kolyan.pathfinder.controller.lore.request.CreateLoreRequest;
import ru.kolyan.pathfinder.controller.lore.request.UpdateByIdLoreRequest;
import ru.kolyan.pathfinder.controller.lore.response.GetAllLoreResponse;
import ru.kolyan.pathfinder.controller.lore.response.GetByIdLoreResponse;

import java.util.UUID;

public interface LoreService {
    void create(CreateLoreRequest request);

    GetByIdLoreResponse getById(UUID id);

    GetAllLoreResponse getAll();

    void deleteById(UUID id);

    void update(UpdateByIdLoreRequest request, UUID id);
}
