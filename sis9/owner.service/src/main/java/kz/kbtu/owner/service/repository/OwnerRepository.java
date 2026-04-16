package kz.kbtu.owner.service.repository;

import kz.kbtu.owner.service.domain.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OwnerRepository extends JpaRepository<Owner, UUID> {
}
