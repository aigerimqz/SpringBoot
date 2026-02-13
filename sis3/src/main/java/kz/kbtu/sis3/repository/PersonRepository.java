package kz.kbtu.sis3.repository;

import kz.kbtu.sis3.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

}
