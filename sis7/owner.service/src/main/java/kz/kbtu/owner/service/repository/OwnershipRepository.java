package kz.kbtu.owner.service.repository;

import kz.kbtu.owner.service.domain.Ownership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OwnershipRepository extends JpaRepository<Ownership, UUID> {
}
