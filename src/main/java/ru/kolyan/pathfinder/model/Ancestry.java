package ru.kolyan.pathfinder.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "ancestries", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ancestry {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true, nullable = false)
    private String name;
    private Integer hp;
    private String size;
    private Integer speed;
    @Column(nullable = false)
    private UUID attributeComboId;
    @Column(columnDefinition = "TEXT")
    private String description;
}
