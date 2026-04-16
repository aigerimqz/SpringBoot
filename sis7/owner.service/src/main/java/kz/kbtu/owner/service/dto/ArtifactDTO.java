package kz.kbtu.owner.service.dto;


import lombok.Data;

import java.util.UUID;

@Data
public class ArtifactDTO {
    private UUID id;
    private String name;
    private String origin;
    private String currentOwnerName;
    private String currentOwnerEmail;
}
