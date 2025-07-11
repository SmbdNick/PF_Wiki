package ru.kolyan.pathfinder.service.api;

import ru.kolyan.pathfinder.controller.skillfeat.request.AddSkillFeatTraitsRequest;
import ru.kolyan.pathfinder.controller.skillfeat.request.CreateSkillFeatRequest;
import ru.kolyan.pathfinder.controller.skillfeat.request.DeleteSkillFeatTraitsRequest;
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

    void addTraits(UUID id, AddSkillFeatTraitsRequest request);

    void deleteTraits(UUID id, DeleteSkillFeatTraitsRequest request);
}
