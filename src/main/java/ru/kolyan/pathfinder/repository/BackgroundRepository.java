package ru.kolyan.pathfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolyan.pathfinder.model.Background;

import java.util.UUID;

public interface BackgroundRepository extends JpaRepository<Background, UUID> {
}