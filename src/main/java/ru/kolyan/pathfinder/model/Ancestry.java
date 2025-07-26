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
@Table(name = "ancestries")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ancestry {
    @Id
    private UUID id;
    private String name;
    private Integer hp;
    private String size;
    private Integer speed;
    private UUID attributeComboId;
    private String description;
}
