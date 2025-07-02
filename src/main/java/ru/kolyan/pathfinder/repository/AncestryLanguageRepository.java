package ru.kolyan.pathfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolyan.pathfinder.model.AncestryLanguage;

import java.util.List;
import java.util.UUID;

public interface AncestryLanguageRepository extends JpaRepository<AncestryLanguage, UUID> {
    List<AncestryLanguage> findAllByAncestryId(UUID ancestryId);

    void deleteAllByAncestryIdAndLanguageIdIn(UUID ancestryId, List<UUID> languageIds);

    boolean existsByAncestryIdAndLanguageIdIn(UUID ancestryId, List<UUID> languageIds);
}
