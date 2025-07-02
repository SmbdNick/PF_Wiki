package ru.kolyan.pathfinder.service.api;

import ru.kolyan.pathfinder.controller.skillfeat.request.AddTraitsRequest;
import ru.kolyan.pathfinder.controller.skillfeat.request.CreateSkillFeatRequest;
import ru.kolyan.pathfinder.controller.skillfeat.request.DeleteTraitsRequest;
import ru.kolyan.pathfinder.controller.skillfeat.request.UpdateByIdSkillFeatRequest;
import ru.kolyan.pathfinder.controller.skillfeat.response.GetAllSkillFeatResponse;
import ru.kolyan.pathfinder.controller.skillfeat.response.GetByIdSkillFeatResponse;

import java.util.UUID;

public interface SkillFeatService {
    void create(CreateSkillFeatRequest request);

    GetByIdSkillFeatResponse getById(UUID id);

    GetAllSkillFeatResponse getAll();

    void deleteById(UUID id);

    void update(UpdateByIdSkillFeatRequest request, UUID id);

    void addTraits(UUID id, AddTraitsRequest request);

    void deleteTraits(UUID id, DeleteTraitsRequest request);
}
