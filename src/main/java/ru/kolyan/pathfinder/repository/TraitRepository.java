package ru.kolyan.pathfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolyan.pathfinder.model.Trait;

import java.util.UUID;

public interface TraitRepository extends JpaRepository<Trait, UUID> {
}
