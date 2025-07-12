package ru.kolyan.pathfinder.mapper;

import org.springframework.stereotype.Component;
import ru.kolyan.pathfinder.controller.masterytier.request.CreateMasteryTierRequest;
import ru.kolyan.pathfinder.controller.masterytier.response.GetAllMasteryTierResponse;
import ru.kolyan.pathfinder.controller.masterytier.response.GetByIdMasteryTierResponse;
import ru.kolyan.pathfinder.model.MasteryTier;

@Component
public class MasteryTierMapper {
    public MasteryTier fromCreateDto(CreateMasteryTierRequest request) {
        MasteryTier masteryTier = new MasteryTier();

        masteryTier.setName(request.getName());

        return masteryTier;
    }

    public GetByIdMasteryTierResponse toGetByIdDto(MasteryTier masteryTier) {
        return GetByIdMasteryTierResponse.builder()
                .id(masteryTier.getId())
                .name(masteryTier.getName())
                .build();
    }

    public GetAllMasteryTierResponse.MasteryTier toGetAllContentDto(MasteryTier masteryTier) {
        return GetAllMasteryTierResponse.MasteryTier.builder()
                .id(masteryTier.getId())
                .name(masteryTier.getName())
                .build();
    }
}
