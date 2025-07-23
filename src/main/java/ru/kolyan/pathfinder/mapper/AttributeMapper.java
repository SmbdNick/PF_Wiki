package ru.kolyan.pathfinder.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.kolyan.pathfinder.controller.attribute.request.CreateAttributeComboRequest;
import ru.kolyan.pathfinder.controller.attribute.request.CreateAttributeRequest;
import ru.kolyan.pathfinder.controller.attribute.response.GetAllAttributeComboResponse;
import ru.kolyan.pathfinder.controller.attribute.response.GetAllAttributeResponse;
import ru.kolyan.pathfinder.controller.attribute.response.GetByIdAttributeResponse;
import ru.kolyan.pathfinder.model.Attribute;
import ru.kolyan.pathfinder.model.AttributeCombo;

@Mapper(componentModel = "spring")
public interface AttributeMapper {
    @Mapping(target = "id", ignore = true)
    Attribute fromCreateDto(CreateAttributeRequest request);

    GetByIdAttributeResponse toGetByIdDto(Attribute attribute);

    GetAllAttributeResponse.Attribute toGetAllContentDto(Attribute attribute);

    @Mapping(target = "id", ignore = true)
    AttributeCombo fromCreateComboDto(CreateAttributeComboRequest request);

    GetAllAttributeComboResponse.AttributeCombo toGetAllContentComboDto(AttributeCombo attributeCombo);
}
