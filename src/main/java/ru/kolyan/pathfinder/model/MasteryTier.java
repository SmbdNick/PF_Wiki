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
@Table(name = "mastery_tiers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MasteryTier {
    @Id
    private UUID id;
    private String name;
}
