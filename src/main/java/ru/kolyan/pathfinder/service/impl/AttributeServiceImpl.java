package ru.kolyan.pathfinder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.kolyan.pathfinder.controller.attribute.request.CreateAttributeComboRequest;
import ru.kolyan.pathfinder.controller.attribute.request.CreateAttributeRequest;
import ru.kolyan.pathfinder.controller.attribute.request.UpdateByIdAttributeComboRequest;
import ru.kolyan.pathfinder.controller.attribute.request.UpdateByIdAttributeRequest;
import ru.kolyan.pathfinder.controller.attribute.response.GetAllAttributeComboResponse;
import ru.kolyan.pathfinder.controller.attribute.response.GetAllAttributeResponse;
import ru.kolyan.pathfinder.controller.attribute.response.GetByIdAttributeResponse;
import ru.kolyan.pathfinder.exception.ConflictException;
import ru.kolyan.pathfinder.exception.NotFoundException;
import ru.kolyan.pathfinder.model.Attribute;
import ru.kolyan.pathfinder.model.AttributeCombo;
import ru.kolyan.pathfinder.repository.AttributeComboRepository;
import ru.kolyan.pathfinder.repository.AttributeRepository;
import ru.kolyan.pathfinder.service.api.AttributeService;
import ru.kolyan.pathfinder.util.ErrorMsgConstants;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttributeServiceImpl implements AttributeService {
    private final AttributeRepository attributeRepository;
    private final AttributeComboRepository attributeComboRepository;

    private static final String ENTITY_ATTRIBUTE = "Атрибут";
    private static final String ENTITY_ATTRIBUTE_COMBO = "Комбо Атрибутов";

    @Override
    public void create(CreateAttributeRequest request) {
        Attribute attribute = new Attribute();
        attribute.setName(request.getName());

        try {
            attributeRepository.save(attribute);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(ErrorMsgConstants.conflict(ENTITY_ATTRIBUTE, attribute.getName()));
        }
    }

    @Override
    public GetByIdAttributeResponse getById(UUID id) {
        Attribute attribute = attributeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_ATTRIBUTE, id)));

        return GetByIdAttributeResponse.builder()
                .id(attribute.getId())
                .name(attribute.getName())
                .build();
    }

    @Override
    public GetAllAttributeResponse getAll() {
        List<Attribute> attributeList = attributeRepository.findAll();
        List<GetAllAttributeResponse.Attribute> content = attributeList.stream()
                .map(attribute -> GetAllAttributeResponse.Attribute.builder()
                        .id(attribute.getId())
                        .name(attribute.getName())
                        .build())
                .toList();

        return GetAllAttributeResponse.builder()
                .content(content)
                .build();
    }

    @Override
    public void deleteById(UUID id) {
        if (!attributeRepository.existsById(id)) {
            throw new NotFoundException(ErrorMsgConstants.notFound(ENTITY_ATTRIBUTE, id));
        }
        attributeRepository.deleteById(id);
    }

    @Override
    public void update(UpdateByIdAttributeRequest request, UUID id) {
        Attribute attribute = attributeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_ATTRIBUTE, id)));

        Optional.ofNullable(request.getName())
                .filter(s -> !s.trim().isEmpty())
                .ifPresent(attribute::setName);

        attributeRepository.save(attribute);
    }

    @Override
    public void createCombo(CreateAttributeComboRequest request) {
        AttributeCombo attributeCombo = new AttributeCombo();
        attributeCombo.setAttributeId1(request.getAttributeId1());
        attributeCombo.setAttributeId2(request.getAttributeId2());
        attributeCombo.setComboName(request.getComboName());

        attributeComboRepository.save(attributeCombo);
    }

    @Override
    public void updateCombo(UpdateByIdAttributeComboRequest request, UUID id) {
        AttributeCombo attributeCombo = attributeComboRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_ATTRIBUTE_COMBO, id)));

        Optional.ofNullable(request.getAttributeId1())
                .ifPresent(attributeCombo::setAttributeId1);

        Optional.ofNullable(request.getAttributeId2())
                .ifPresent(attributeCombo::setAttributeId2);

        Optional.ofNullable(request.getComboName())
                .filter(s -> !s.trim().isEmpty())
                .ifPresent(attributeCombo::setComboName);

        attributeComboRepository.save(attributeCombo);
    }

    @Override
    public void deleteComboById(UUID id) {
        if (!attributeComboRepository.existsById(id)) {
            throw new NotFoundException(ErrorMsgConstants.notFound(ENTITY_ATTRIBUTE_COMBO, id));
        }

        attributeComboRepository.deleteById(id);
    }

    @Override
    public GetAllAttributeComboResponse getAllCombo() {
        List<AttributeCombo> attributeComboList = attributeComboRepository.findAll();
        List<GetAllAttributeComboResponse.AttributeCombo> content = attributeComboList.stream()
                .map(attributeCombo -> GetAllAttributeComboResponse.AttributeCombo.builder()
                        .attributeId1(attributeCombo.getAttributeId1())
                        .attributeId2(attributeCombo.getAttributeId2())
                        .comboName(attributeCombo.getComboName())
                        .build())
                .toList();

        return GetAllAttributeComboResponse.builder()
                .content(content)
                .build();
    }
}
