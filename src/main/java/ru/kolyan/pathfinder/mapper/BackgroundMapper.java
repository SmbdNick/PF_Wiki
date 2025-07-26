package ru.kolyan.pathfinder.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kolyan.pathfinder.controller.background.request.CreateBackgroundRequest;
import ru.kolyan.pathfinder.controller.background.response.GetAllBackgroundResponse;
import ru.kolyan.pathfinder.controller.background.response.GetByIdBackgroundResponse;
import ru.kolyan.pathfinder.model.Background;

@Mapper(componentModel = "spring")
public interface BackgroundMapper {
    @Mapping(target = "id", ignore = true)
    Background fromCreateDto(CreateBackgroundRequest request);

    @Mapping(source = "comboName", target = "attributeComboName")
    GetByIdBackgroundResponse toGetByIdDto(Background background, String skillFeatName, String skillName,
                                           String loreName, String comboName);

    @Mapping(source = "comboName", target = "attributeComboName")
    GetAllBackgroundResponse.Background getAllContentDto(Background background, String skillFeatName,
                                                         String skillName, String loreName, String comboName);
}
