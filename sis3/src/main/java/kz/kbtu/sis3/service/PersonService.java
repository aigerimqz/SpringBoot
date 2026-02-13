package kz.kbtu.sis3.service;


import kz.kbtu.sis3.domain.Person;
import kz.kbtu.sis3.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person savePerson(String name, String surname, String role){
        Person person = Person.builder().name(name).surname(surname).role(role).build();
        return personRepository.save(person);
    }

    public List<Person> getAllPersons(){
        return personRepository.findAll();
    }
}
