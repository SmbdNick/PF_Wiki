package ru.kolyan.pathfinder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.kolyan.pathfinder.controller.attribute.request.CreateAttributeRequest;
import ru.kolyan.pathfinder.controller.attribute.request.UpdateByIdAttributeRequest;
import ru.kolyan.pathfinder.controller.attribute.response.GetAllAttributeResponse;
import ru.kolyan.pathfinder.controller.attribute.response.GetByIdAttributeResponse;
import ru.kolyan.pathfinder.exception.ConflictException;
import ru.kolyan.pathfinder.exception.NotFoundException;
import ru.kolyan.pathfinder.model.Attribute;
import ru.kolyan.pathfinder.repository.AttributeRepository;
import ru.kolyan.pathfinder.service.api.AttributeService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttributeServiceImpl implements AttributeService {
    private final AttributeRepository attributeRepository;

    @Override
    public void create(CreateAttributeRequest request) {
        Attribute attribute = new Attribute();
        attribute.setName(request.getName());

        try {
            attributeRepository.save(attribute);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Атрибут с таким именем уже ест");
        }
    }

    @Override
    public GetByIdAttributeResponse getById(UUID id) {
        Attribute attribute = attributeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Атрибута с таким ID нету"));

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
            throw new NotFoundException("Атрибутика с таким ID нету");
        }
        attributeRepository.deleteById(id);
    }

    @Override
    public void update(UpdateByIdAttributeRequest request, UUID id) {
        Attribute attribute = attributeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Атрибутика с таким ID нету"));

        Optional.ofNullable(request.getName())
                .ifPresent(attribute::setName);

        attributeRepository.save(attribute);
    }
}
