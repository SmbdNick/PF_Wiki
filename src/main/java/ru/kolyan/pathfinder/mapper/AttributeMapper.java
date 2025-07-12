package ru.kolyan.pathfinder.mapper;

import org.springframework.stereotype.Component;
import ru.kolyan.pathfinder.controller.attribute.request.CreateAttributeComboRequest;
import ru.kolyan.pathfinder.controller.attribute.request.CreateAttributeRequest;
import ru.kolyan.pathfinder.controller.attribute.response.GetAllAttributeComboResponse;
import ru.kolyan.pathfinder.controller.attribute.response.GetAllAttributeResponse;
import ru.kolyan.pathfinder.controller.attribute.response.GetByIdAttributeResponse;
import ru.kolyan.pathfinder.model.Attribute;
import ru.kolyan.pathfinder.model.AttributeCombo;

@Component
public class AttributeMapper {
    public Attribute fromCreateDto(CreateAttributeRequest request) {
        Attribute attribute = new Attribute();

        attribute.setName(request.getName());

        return attribute;
    }

    public GetByIdAttributeResponse toGetByIdDto(Attribute attribute) {
        return GetByIdAttributeResponse.builder()
                .id(attribute.getId())
                .name(attribute.getName())
                .build();
    }

    public GetAllAttributeResponse.Attribute toGetAllContentDto(Attribute attribute) {
        return GetAllAttributeResponse.Attribute.builder()
                .id(attribute.getId())
                .name(attribute.getName())
                .build();
    }

    public AttributeCombo fromCreateComboDto(CreateAttributeComboRequest request) {
        AttributeCombo attributeCombo = new AttributeCombo();

        attributeCombo.setAttributeId1(request.getAttributeId1());
        attributeCombo.setAttributeId2(request.getAttributeId2());
        attributeCombo.setComboName(request.getComboName());

        return attributeCombo;
    }

    public GetAllAttributeComboResponse.AttributeCombo toGetAllContentComboDto(AttributeCombo attributeCombo) {
        return GetAllAttributeComboResponse.AttributeCombo.builder()
                .attributeId1(attributeCombo.getAttributeId1())
                .attributeId2(attributeCombo.getAttributeId2())
                .comboName(attributeCombo.getComboName())
                .build();
    }
}
