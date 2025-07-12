package ru.kolyan.pathfinder.mapper;

import org.springframework.stereotype.Component;
import ru.kolyan.pathfinder.controller.skillfeat.request.AddSkillFeatTraitsRequest;
import ru.kolyan.pathfinder.controller.skillfeat.request.CreateSkillFeatRequest;
import ru.kolyan.pathfinder.controller.skillfeat.response.GetAllSkillFeatResponse;
import ru.kolyan.pathfinder.controller.skillfeat.response.GetByIdSkillFeatResponse;
import ru.kolyan.pathfinder.model.SkillFeat;
import ru.kolyan.pathfinder.model.SkillFeatTrait;

import java.util.ArrayList;
import java.util.List;

@Component
public class SkillFeatMapper {
    public SkillFeat fromCreateDto(CreateSkillFeatRequest request) {
        SkillFeat skillFeat = new SkillFeat();

        skillFeat.setName(request.getName());
        skillFeat.setDescription(request.getDescription());

        return skillFeat;
    }

    public GetByIdSkillFeatResponse toGetByIdDto(SkillFeat skillFeat, List<String> traits) {
        return GetByIdSkillFeatResponse.builder()
                .id(skillFeat.getId())
                .name(skillFeat.getName())
                .description(skillFeat.getDescription())
                .traitList(traits)
                .build();
    }

    public GetAllSkillFeatResponse.SkillFeat toGetAllContentDto(SkillFeat skillFeat) {
        return GetAllSkillFeatResponse.SkillFeat.builder()
                .id(skillFeat.getId())
                .name(skillFeat.getName())
                .description(skillFeat.getDescription())
                .build();
    }

    public List<SkillFeatTrait> fromAddTraitsDto(AddSkillFeatTraitsRequest request, SkillFeat skillFeat) {
        List<SkillFeatTrait> skillFeatTraitList = new ArrayList<>();

        request.getTraitIdList().forEach(traitId -> {
            SkillFeatTrait skillFeatTrait = new SkillFeatTrait();
            skillFeatTrait.setTraitId(traitId);
            skillFeatTrait.setSkillFeatId(skillFeat.getId());

            skillFeatTraitList.add(skillFeatTrait);
        });

        return skillFeatTraitList;
    }
}
