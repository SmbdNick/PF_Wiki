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
@Table(name = "backgrounds")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Background {
    @Id
    private UUID id;
    private String name;
    private String description;
    private UUID skillFeatId;
    private UUID skillId;
    private UUID loreId;
    private UUID attributeComboId;
}
