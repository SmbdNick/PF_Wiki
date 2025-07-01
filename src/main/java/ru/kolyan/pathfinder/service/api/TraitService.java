package ru.kolyan.pathfinder.service.api;

import ru.kolyan.pathfinder.controller.trait.request.CreateTraitRequest;
import ru.kolyan.pathfinder.controller.trait.request.UpdateByIdTraitRequest;
import ru.kolyan.pathfinder.controller.trait.response.GetAllTraitResponse;

import java.util.UUID;

public interface TraitService {
    void create(CreateTraitRequest request);

    void update(UpdateByIdTraitRequest request, UUID id);

    void deleteById(UUID id);

    GetAllTraitResponse getAll();
}
