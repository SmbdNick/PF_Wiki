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
@Table(name = "attribute_combos", uniqueConstraints = @UniqueConstraint(columnNames = {"combo_name"}))
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AttributeCombo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID attributeId1;
    private UUID attributeId2;
    @Column(unique = true, nullable = false)
    private String comboName;
}
