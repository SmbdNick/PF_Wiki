package ru.kolyan.pathfinder.service.api;

import ru.kolyan.pathfinder.controller.ancestry.request.AddAncestryLanguageRequest;
import ru.kolyan.pathfinder.controller.ancestry.request.AddAncestryTraitsRequest;
import ru.kolyan.pathfinder.controller.ancestry.request.CreateAncestryRequest;
import ru.kolyan.pathfinder.controller.ancestry.request.DeleteAncestryLanguageRequest;
import ru.kolyan.pathfinder.controller.ancestry.request.DeleteAncestryTraitsRequest;
import ru.kolyan.pathfinder.controller.ancestry.request.UpdateByIdAncestryRequest;
import ru.kolyan.pathfinder.controller.ancestry.response.GetAllAncestryResponse;
import ru.kolyan.pathfinder.controller.ancestry.response.GetByIdAncestryResponse;

import java.util.UUID;

public interface AncestryService {
    void create(CreateAncestryRequest request);

    GetByIdAncestryResponse getById(UUID id);

    GetAllAncestryResponse getAll();

    void deleteById(UUID id);

    void update(UpdateByIdAncestryRequest request, UUID id);

    void addTraits(UUID id, AddAncestryTraitsRequest request);

    void deleteTraits(UUID id, DeleteAncestryTraitsRequest request);

    void addLanguages(UUID id, AddAncestryLanguageRequest request);

    void deleteLanguages(UUID id, DeleteAncestryLanguageRequest request);
}
