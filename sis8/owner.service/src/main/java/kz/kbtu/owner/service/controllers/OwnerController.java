package kz.kbtu.owner.service.controllers;


import kz.kbtu.owner.service.domain.Owner;
import kz.kbtu.owner.service.domain.Ownership;
import kz.kbtu.owner.service.repository.OwnerRepository;
import kz.kbtu.owner.service.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @Autowired
    private OwnerRepository ownerRepository;

    @PostMapping
    public Owner createOwner(@RequestBody Owner owner){
        return ownerRepository.save(owner);
    }

    @GetMapping
    public List<Owner> getAll(){
        return ownerRepository.findAll();
    }

    @PostMapping("/assign")
    public Ownership assignOwner(@RequestParam UUID artifactId, @RequestParam UUID ownerId){
        return ownerService.assignOwner(artifactId, ownerId);
    }
}
