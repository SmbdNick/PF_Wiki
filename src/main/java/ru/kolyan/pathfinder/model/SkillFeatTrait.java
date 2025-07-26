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
@Table(name = "skill_feat_traits")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SkillFeatTrait {
    @Id
    private UUID id;
    private UUID traitId;
    private UUID skillFeatId;
}
