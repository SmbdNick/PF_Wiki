package ru.kolyan.pathfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolyan.pathfinder.model.MasteryTier;

import java.util.UUID;

public interface MasteryTierRepository extends JpaRepository<MasteryTier, UUID> {
}