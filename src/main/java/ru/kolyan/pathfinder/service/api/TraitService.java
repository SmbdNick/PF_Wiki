package ru.kolyan.pathfinder.service.api;

import ru.kolyan.pathfinder.controller.trait.request.CreateTraitRequest;
import ru.kolyan.pathfinder.controller.trait.request.UpdateByIdTraitRequest;
import ru.kolyan.pathfinder.controller.trait.response.GetAllTraitResponse;
import ru.kolyan.pathfinder.service.dto.GetTraitDto;
import ru.kolyan.pathfinder.service.dto.GetTraitsFilterDto;

import java.util.List;
import java.util.UUID;

public interface TraitService {
    void create(CreateTraitRequest request);

    void update(UpdateByIdTraitRequest request, UUID id);

    void deleteById(UUID id);

    GetAllTraitResponse getAll();

    List<GetTraitDto> getAllByFilter(GetTraitsFilterDto filter);
}
