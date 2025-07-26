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
@Table(name = "ancestry_traits")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AncestryTrait {
    @Id
    private UUID id;
    private UUID traitId;
    private UUID ancestryId;
}
