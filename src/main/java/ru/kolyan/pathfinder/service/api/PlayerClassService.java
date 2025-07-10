package ru.kolyan.pathfinder.service.api;

import ru.kolyan.pathfinder.controller.playerclass.request.CreatePlayerClassRequest;
import ru.kolyan.pathfinder.controller.playerclass.request.UpdateByIdPlayerClassRequest;
import ru.kolyan.pathfinder.controller.playerclass.response.GetAllPlayerClassResponse;
import ru.kolyan.pathfinder.controller.playerclass.response.GetByIdPlayerClassResponse;

import java.util.UUID;

public interface PlayerClassService {
    void create(CreatePlayerClassRequest request);

    GetByIdPlayerClassResponse getById(UUID id);

    GetAllPlayerClassResponse getAll();

    void deleteById(UUID id);

    void update(UpdateByIdPlayerClassRequest request, UUID id);
}
