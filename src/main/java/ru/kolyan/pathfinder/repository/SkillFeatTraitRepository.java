package ru.kolyan.pathfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import ru.kolyan.pathfinder.model.SkillFeatTrait;

import java.util.List;
import java.util.UUID;

@Repository
public interface SkillFeatTraitRepository extends JpaRepository<SkillFeatTrait, UUID> {
    List<SkillFeatTrait> findAllBySkillFeatId(UUID skillFeatId);

    @Modifying
    void deleteAllBySkillFeatIdAndTraitIdIn(UUID skillFeatId, List<UUID> traitIdList);

    boolean existsBySkillFeatIdAndTraitIdIn(UUID skillFeatId, List<UUID> traitIdList);
}
