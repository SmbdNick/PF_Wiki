package ru.kolyan.pathfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolyan.pathfinder.model.PlayerClass;

import java.util.UUID;

public interface PlayerClassRepository extends JpaRepository<PlayerClass, UUID> {
}