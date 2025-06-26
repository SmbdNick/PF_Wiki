package ru.kolyan.pathfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kolyan.pathfinder.model.SkillFeat;

import java.util.UUID;

@Repository
public interface SkillFeatRepository extends JpaRepository<SkillFeat, UUID> {
}
