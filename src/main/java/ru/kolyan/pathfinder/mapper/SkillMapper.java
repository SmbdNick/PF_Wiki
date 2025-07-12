package ru.kolyan.pathfinder.mapper;

import org.springframework.stereotype.Component;
import ru.kolyan.pathfinder.controller.skill.request.CreateSkillRequest;
import ru.kolyan.pathfinder.controller.skill.response.GetAllSkillResponse;
import ru.kolyan.pathfinder.controller.skill.response.GetByIdSkillResponse;
import ru.kolyan.pathfinder.model.Skill;

@Component
public class SkillMapper {
    public Skill fromCreateDto(CreateSkillRequest request) {
        Skill skill = new Skill();

        skill.setName(request.getName());

        return skill;
    }

    public GetByIdSkillResponse toGetByIdDto(Skill skill) {
        return GetByIdSkillResponse.builder()
                .id(skill.getId())
                .name(skill.getName())
                .build();
    }

    public GetAllSkillResponse.Skill toGetAllContentDto(Skill skill) {
        return GetAllSkillResponse.Skill.builder()
                .id(skill.getId())
                .name(skill.getName())
                .build();
    }
}
