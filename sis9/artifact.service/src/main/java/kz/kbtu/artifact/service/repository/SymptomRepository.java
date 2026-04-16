package kz.kbtu.artifact.service.repository;

import kz.kbtu.artifact.service.domain.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SymptomRepository extends JpaRepository<Symptom, UUID> {
    List<Symptom> findByArtifactId(UUID artifactId);
}
