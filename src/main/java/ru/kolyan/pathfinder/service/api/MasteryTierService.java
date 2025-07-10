package ru.kolyan.pathfinder.service.api;

import ru.kolyan.pathfinder.controller.masterytier.request.CreateMasteryTierRequest;
import ru.kolyan.pathfinder.controller.masterytier.request.UpdateByIdMasteryTierRequest;
import ru.kolyan.pathfinder.controller.masterytier.response.GetAllMasteryTierResponse;
import ru.kolyan.pathfinder.controller.masterytier.response.GetByIdMasteryTierResponse;

import java.util.UUID;

public interface MasteryTierService {
    void create(CreateMasteryTierRequest request);

    GetByIdMasteryTierResponse getById(UUID id);

    GetAllMasteryTierResponse getAll();

    void deleteById(UUID id);

    void update(UpdateByIdMasteryTierRequest request, UUID id);
}
