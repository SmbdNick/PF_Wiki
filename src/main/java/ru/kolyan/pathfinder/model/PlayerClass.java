package ru.kolyan.pathfinder.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String name;
    private Integer hpPerLvl;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private UUID attributeComboId;
}
