package ru.kolyan.pathfinder.mapper;

import org.springframework.stereotype.Component;
import ru.kolyan.pathfinder.controller.background.request.CreateBackgroundRequest;
import ru.kolyan.pathfinder.controller.background.response.GetAllBackgroundResponse;
import ru.kolyan.pathfinder.controller.background.response.GetByIdBackgroundResponse;
import ru.kolyan.pathfinder.model.Background;

@Component
public class BackgroundMapper {
    public Background fromCreateDto(CreateBackgroundRequest request) {
        Background background = new Background();

        background.setName(request.getName());
        background.setDescription(request.getDescription());
        background.setAttributeComboId(request.getAttributeComboId());
        background.setLoreId(request.getLoreId());
        background.setSkillFeatId(request.getSkillFeatId());
        background.setSkillId(request.getSkillId());

        return background;
    }

    public GetByIdBackgroundResponse toGetByIdDto(Background background, String skillFeatName, String skillName,
                                                  String loreName, String comboName) {
        return GetByIdBackgroundResponse.builder()
                .id(background.getId())
                .name(background.getName())
                .description(background.getDescription())
                .skillFeatId(background.getSkillFeatId())
                .skillFeatName(skillFeatName)
                .skillId(background.getSkillId())
                .skillName(skillName)
                .loreId(background.getLoreId())
                .loreName(loreName)
                .attributeComboId(background.getAttributeComboId())
                .attributeComboName(comboName)
                .build();
    }

    public GetAllBackgroundResponse.Background getAllContentDto(Background background, String skillFeatName,
                                                                String skillName, String loreName, String comboName) {
        return GetAllBackgroundResponse.Background.builder()
                .id(background.getId())
                .name(background.getName())
                .description(background.getDescription())
                .skillFeatId(background.getSkillFeatId())
                .skillFeatName(skillFeatName)
                .skillId(background.getSkillId())
                .skillName(skillName)
                .loreId(background.getLoreId())
                .loreName(loreName)
                .attributeComboId(background.getAttributeComboId())
                .attributeComboName(comboName)
                .build();
    }
}
