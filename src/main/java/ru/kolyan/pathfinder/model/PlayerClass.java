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
@Table(name = "player_classes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlayerClass {
    @Id
    private UUID id;
    private String name;
    private Integer hpPerLvl;
    private String description;
    private UUID attributeComboId;
}
