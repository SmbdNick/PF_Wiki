package ru.kolyan.pathfinder.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.kolyan.pathfinder.controller.trait.request.CreateTraitRequest;
import ru.kolyan.pathfinder.controller.trait.request.UpdateByIdTraitRequest;
import ru.kolyan.pathfinder.controller.trait.response.GetAllTraitResponse;
import ru.kolyan.pathfinder.exception.ConflictException;
import ru.kolyan.pathfinder.exception.NotFoundException;
import ru.kolyan.pathfinder.model.Trait;
import ru.kolyan.pathfinder.repository.TraitRepository;
import ru.kolyan.pathfinder.service.api.TraitService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TraitServiceImpl implements TraitService {
    private final TraitRepository traitRepository;

    @Override
    public void create(CreateTraitRequest request) {
        Trait trait = new Trait();

        trait.setName(request.getName());
        trait.setDescription(request.getDescription());

        try {
            traitRepository.save(trait);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException("Трэйт с таким назаванием уже ест");
        }
    }

    @Override
    public void update(UpdateByIdTraitRequest request, UUID id) {
        Trait trait = traitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Трэйта с таким ID нету"));

        Optional.ofNullable(request.getName())
                .ifPresent(trait::setName);

        Optional.ofNullable(request.getDescription())
                .ifPresent(trait::setDescription);

        traitRepository.save(trait);
    }

    @Override
    public void deleteById(UUID id) {
        if (!traitRepository.existsById(id)) {
            throw new NotFoundException("Трэйта с таким ID нету");
        }
        traitRepository.deleteById(id);
    }

    @Override
    public GetAllTraitResponse getAll() {
        List<Trait> traitList = traitRepository.findAll();
        List<GetAllTraitResponse.Trait> content = traitList.stream()
                .map(trait -> GetAllTraitResponse.Trait.builder()
                        .id(trait.getId())
                        .name(trait.getName())
                        .description(trait.getDescription())
                        .build())
                .toList();

        return GetAllTraitResponse.builder()
                .content(content)
                .build();
    }
}
