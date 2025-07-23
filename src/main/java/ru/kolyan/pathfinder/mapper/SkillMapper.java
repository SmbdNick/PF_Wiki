package ru.kolyan.pathfinder.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kolyan.pathfinder.controller.skill.request.CreateSkillRequest;
import ru.kolyan.pathfinder.controller.skill.response.GetAllSkillResponse;
import ru.kolyan.pathfinder.controller.skill.response.GetByIdSkillResponse;
import ru.kolyan.pathfinder.model.Skill;

@Mapper(componentModel = "spring")
public interface SkillMapper {
    @Mapping(target = "id", ignore = true)
    Skill fromCreateDto(CreateSkillRequest request);

    GetByIdSkillResponse toGetByIdDto(Skill skill);

    GetAllSkillResponse.Skill toGetAllContentDto(Skill skill);
}
