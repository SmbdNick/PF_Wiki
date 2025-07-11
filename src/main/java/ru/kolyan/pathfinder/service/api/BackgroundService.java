package ru.kolyan.pathfinder.service.api;

import ru.kolyan.pathfinder.controller.background.request.CreateBackgroundRequest;
import ru.kolyan.pathfinder.controller.background.request.UpdateByIdBackgroundRequest;
import ru.kolyan.pathfinder.controller.background.response.GetAllBackgroundResponse;
import ru.kolyan.pathfinder.controller.background.response.GetByIdBackgroundResponse;

import java.util.UUID;

public interface BackgroundService {
    void create(CreateBackgroundRequest request);

    GetByIdBackgroundResponse getById(UUID id);

    GetAllBackgroundResponse getAll();

    void deleteById(UUID id);

    void update(UpdateByIdBackgroundRequest request, UUID id);
}
