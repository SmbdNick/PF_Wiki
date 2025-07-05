package ru.kolyan.pathfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolyan.pathfinder.model.AttributeCombo;

import java.util.UUID;

public interface AttributeComboRepository extends JpaRepository<AttributeCombo, UUID> {
}
