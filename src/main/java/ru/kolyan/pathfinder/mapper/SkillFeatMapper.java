package ru.kolyan.pathfinder.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kolyan.pathfinder.controller.skillfeat.request.AddSkillFeatTraitsRequest;
import ru.kolyan.pathfinder.controller.skillfeat.request.CreateSkillFeatRequest;
import ru.kolyan.pathfinder.controller.skillfeat.response.GetAllSkillFeatResponse;
import ru.kolyan.pathfinder.controller.skillfeat.response.GetByIdSkillFeatResponse;
import ru.kolyan.pathfinder.model.SkillFeat;
import ru.kolyan.pathfinder.model.SkillFeatTrait;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface SkillFeatMapper {
    @Mapping(target = "id", ignore = true)
    SkillFeat fromCreateDto(CreateSkillFeatRequest request);

    @Mapping(source = "traits", target = "traitList")
    GetByIdSkillFeatResponse toGetByIdDto(SkillFeat skillFeat, List<String> traits);

    GetAllSkillFeatResponse.SkillFeat toGetAllContentDto(SkillFeat skillFeat);

    default List<SkillFeatTrait> fromAddTraitsDto(AddSkillFeatTraitsRequest request, SkillFeat skillFeat) {
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
