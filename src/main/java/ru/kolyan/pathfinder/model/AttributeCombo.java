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
@Table(name = "attribute_combos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AttributeCombo {
    @Id
    private UUID id;
    private UUID attributeId1;
    private UUID attributeId2;
    private String comboName;
}
