package ru.kolyan.pathfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import ru.kolyan.pathfinder.model.AncestryTrait;

import java.util.List;
import java.util.UUID;

public interface AncestryTraitRepository extends JpaRepository<AncestryTrait, UUID> {
    List<AncestryTrait> findAllByAncestryId(UUID ancestryId);

    @Modifying
    void deleteAllByAncestryIdAndTraitIdIn(UUID ancestryId, List<UUID> traitIds);

    boolean existsByAncestryIdAndTraitIdIn(UUID ancestryId, List<UUID> traitIds);

}
