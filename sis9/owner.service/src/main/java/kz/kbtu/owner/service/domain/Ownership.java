package kz.kbtu.owner.service.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ownership")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ownership {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID artifactId;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    private LocalDateTime acquiredAt;
}
