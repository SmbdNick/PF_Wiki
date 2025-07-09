package ru.kolyan.pathfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolyan.pathfinder.model.Lore;

import java.util.UUID;

public interface LoreRepository extends JpaRepository<Lore, UUID> {
}