package ru.kolyan.pathfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolyan.pathfinder.model.ClassMastery;

import java.util.List;
import java.util.UUID;

public interface ClassMasteryRepository extends JpaRepository<ClassMastery, UUID> {
    List<ClassMastery> findAllByPlayerClassId(UUID playerClassId);
}