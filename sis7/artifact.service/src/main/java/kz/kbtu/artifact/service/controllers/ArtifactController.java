package kz.kbtu.artifact.service.controllers;


import kz.kbtu.artifact.service.domain.Artifact;
import kz.kbtu.artifact.service.domain.Symptom;
import kz.kbtu.artifact.service.service.ArtifactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/artifacts")
public class ArtifactController {

    @Autowired
    private ArtifactService artifactService;

    @PostMapping
    public Artifact create(@RequestBody Artifact artifact){
        return artifactService.create(artifact);
    }

    @GetMapping
    public List<Artifact> getAll(){
        return artifactService.getAll();
    }

    @GetMapping("/{id}")
    public Artifact getById(@PathVariable UUID id){
        return artifactService.getById(id);
    }

    @PostMapping("/{id}/assign-owner")
    public Artifact assignOwner(@PathVariable UUID id, @RequestParam String ownerName, @RequestParam String ownerEmail){
        return artifactService.assignOwner(id, ownerName, ownerEmail);
    }
    @PostMapping("/{id}/symptoms")
    public Symptom addSymptom(@PathVariable UUID id, @RequestBody Symptom symptom){
        return artifactService.addSymptom(id, symptom);
    }

    @GetMapping("/{id}/symptoms")
    public List<Symptom> getSymptoms(@PathVariable UUID id){
        return artifactService.getSymptoms(id);
    }


}
