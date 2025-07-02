package ru.kolyan.pathfinder.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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
import ru.kolyan.pathfinder.service.dto.GetTraitDto;
import ru.kolyan.pathfinder.service.dto.GetTraitsFilterDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TraitServiceImpl implements TraitService {
    private final TraitRepository traitRepository;
    private final EntityManager entityManager;

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

    @Override
    public List<GetTraitDto> getAllByFilter(GetTraitsFilterDto filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Trait> query = cb.createQuery(Trait.class);
        Root<Trait> root = query.from(Trait.class);

        query.multiselect(root.get("id"), root.get("name"), root.get("description"))
                .where(root.get("id").in(filter.getTraitIdList()));
        List<Trait> traits = entityManager.createQuery(query).getResultList();

        return traits.stream()
                .map(trait -> GetTraitDto.builder()
                        .id(trait.getId())
                        .name(trait.getName())
                        .description(trait.getDescription())
                        .build()
                )
                .toList();
    }
}
