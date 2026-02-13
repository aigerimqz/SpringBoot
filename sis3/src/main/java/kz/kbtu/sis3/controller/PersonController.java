package kz.kbtu.sis3.controller;


import kz.kbtu.sis3.domain.Person;
import kz.kbtu.sis3.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public Person createItem(@RequestBody Person person){
        return personService.savePerson(
                person.getName(),
                person.getSurname(),
                person.getRole()
        );
    }

    @GetMapping
    public List<Person> getAllPersons(){
        return personService.getAllPersons();
    }
}
