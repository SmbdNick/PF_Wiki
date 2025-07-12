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
import ru.kolyan.pathfinder.mapper.TraitMapper;
import ru.kolyan.pathfinder.model.Trait;
import ru.kolyan.pathfinder.repository.TraitRepository;
import ru.kolyan.pathfinder.service.api.TraitService;
import ru.kolyan.pathfinder.service.dto.GetTraitDto;
import ru.kolyan.pathfinder.service.dto.GetTraitsFilterDto;
import ru.kolyan.pathfinder.util.ErrorMsgConstants;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TraitServiceImpl implements TraitService {
    private final TraitRepository traitRepository;
    private final EntityManager entityManager;
    private final TraitMapper traitMapper;

    private static final String ENTITY_TRAIT = "Трэйт";

    @Override
    public void create(CreateTraitRequest request) {
        Trait trait = traitMapper.fromCreateDto(request);

        try {
            traitRepository.save(trait);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(ErrorMsgConstants.conflict(ENTITY_TRAIT, trait.getName()));
        }
    }

    @Override
    public void update(UpdateByIdTraitRequest request, UUID id) {
        Trait trait = traitRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ErrorMsgConstants.notFound(ENTITY_TRAIT, id)));

        Optional.ofNullable(request.getName())
                .filter(s -> !s.trim().isEmpty())
                .ifPresent(trait::setName);

        Optional.ofNullable(request.getDescription())
                .filter(s -> !s.trim().isEmpty())
                .ifPresent(trait::setDescription);

        try {
            traitRepository.save(trait);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictException(ErrorMsgConstants.conflict(ENTITY_TRAIT, trait.getName()));
        }
    }

    @Override
    public void deleteById(UUID id) {
        if (!traitRepository.existsById(id)) {
            throw new NotFoundException(ErrorMsgConstants.notFound(ENTITY_TRAIT, id));
        }
        traitRepository.deleteById(id);
    }

    @Override
    public GetAllTraitResponse getAll() {
        List<Trait> traitList = traitRepository.findAll();
        List<GetAllTraitResponse.Trait> content = traitList.stream()
                .map(traitMapper::toGetAllContentDto)
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
                .map(traitMapper::toGetServiceDto)
                .toList();
    }
}
