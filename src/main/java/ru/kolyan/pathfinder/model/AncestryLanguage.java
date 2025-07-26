package ru.kolyan.pathfinder.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "ancestry_languages")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AncestryLanguage {
    @Id
    private UUID id;
    private UUID languageId;
    private UUID ancestryId;
}
