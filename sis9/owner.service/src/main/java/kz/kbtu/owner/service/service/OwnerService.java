package kz.kbtu.owner.service.service;


import kz.kbtu.owner.service.client.ArtifactClient;
import kz.kbtu.owner.service.domain.Owner;
import kz.kbtu.owner.service.domain.Ownership;
import kz.kbtu.owner.service.repository.OwnerRepository;
import kz.kbtu.owner.service.repository.OwnershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private OwnershipRepository ownershipRepository;
    @Autowired
    private ArtifactClient artifactClient;

    public Ownership assignOwner(UUID artifactId, UUID ownerId){
        Owner owner = ownerRepository.findById(ownerId).orElseThrow(() -> new RuntimeException("Owner not found"));

        artifactClient.assignOwner(
                artifactId, owner.getName(), owner.getEmail()
        );

        Ownership ownership = Ownership.builder().artifactId(artifactId).owner(owner).acquiredAt(LocalDateTime.now()).build();

        return ownershipRepository.save(ownership);
    }



}
