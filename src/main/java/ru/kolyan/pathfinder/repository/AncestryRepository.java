package ru.kolyan.pathfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolyan.pathfinder.model.Ancestry;

import java.util.UUID;

public interface AncestryRepository extends JpaRepository<Ancestry, UUID> {
}
