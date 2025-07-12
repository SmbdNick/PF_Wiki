package ru.kolyan.pathfinder.mapper;

import org.springframework.stereotype.Component;
import ru.kolyan.pathfinder.controller.trait.request.CreateTraitRequest;
import ru.kolyan.pathfinder.controller.trait.response.GetAllTraitResponse;
import ru.kolyan.pathfinder.model.Trait;
import ru.kolyan.pathfinder.service.dto.GetTraitDto;

@Component
public class TraitMapper {
    public Trait fromCreateDto(CreateTraitRequest request) {
        Trait trait = new Trait();

        trait.setName(request.getName());
        trait.setDescription(request.getDescription());

        return trait;
    }

    public GetAllTraitResponse.Trait toGetAllContentDto(Trait trait) {
        return GetAllTraitResponse.Trait.builder()
                .id(trait.getId())
                .name(trait.getName())
                .description(trait.getDescription())
                .build();
    }

    public GetTraitDto toGetServiceDto(Trait trait) {
        return GetTraitDto.builder()
                .id(trait.getId())
                .name(trait.getName())
                .description(trait.getDescription())
                .build();
    }
}
