package kz.kbtu.artifact.service.repository;

import kz.kbtu.artifact.service.domain.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArtifactRepository extends JpaRepository<Artifact, UUID> {
}
