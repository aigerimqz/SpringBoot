package kz.kbtu.artifact.service.service;

import kz.kbtu.artifact.service.domain.Artifact;
import kz.kbtu.artifact.service.domain.Symptom;
import kz.kbtu.artifact.service.repository.ArtifactRepository;
import kz.kbtu.artifact.service.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ArtifactService {

    @Autowired
    private ArtifactRepository artifactRepository;

    @Autowired
    private SymptomRepository symptomRepository;

    public Artifact create(Artifact artifact){
        return artifactRepository.save(artifact);
    }

    public List<Artifact> getAll(){
        return artifactRepository.findAll();
    }

    public Artifact getById(UUID id){
        return artifactRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found: " + id));
    }

    public Artifact assignOwner(UUID id, String ownerName, String ownerEmail){
        Artifact artifact = getById(id);
        artifact.setCurrentOwnerName(ownerName);
        artifact.setCurrentOwnerEmail(ownerEmail);
        return artifactRepository.save(artifact);
    }


    public Symptom addSymptom(UUID artifactId, Symptom symptom) {
        getById(artifactId);
        symptom.setArtifactId(artifactId);
        return symptomRepository.save(symptom);
    }
    public List<Symptom> getSymptoms(UUID artifactId){
        return symptomRepository.findByArtifactId(artifactId);
    }
}
