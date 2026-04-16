package kz.kbtu.artifact.service.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "cursed_artifacts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artifact {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String origin;
    private String curseType;
    private String dangerType;
    private String currentOwnerName;
    private String currentOwnerEmail;


}
