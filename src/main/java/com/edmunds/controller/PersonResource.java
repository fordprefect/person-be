package com.edmunds.controller;

import com.edmunds.dao.PersonRepository;
import com.edmunds.exception.PersonNotFoundException;
import com.edmunds.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonResource {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/people")
    public List<Person> retrieveAllPeople() {
        return personRepository.findAll();
    }

    @GetMapping("/people/{id}")
    public Person retrievePersonById(@PathVariable long id) {
        Optional<Person> person = personRepository.findById(id);

        if (!person.isPresent())
            throw new PersonNotFoundException("id-" + id);

        return person.get();
    }

    @GetMapping("/people/email/{email}")
    public Person retrievePersonByEmail(@PathVariable String email) {
        byte[] decodedEmail = Base64.getDecoder().decode(email);
        Optional<Person> person = personRepository.findByEmail(new String(decodedEmail));

        if (!person.isPresent())
            throw new PersonNotFoundException("email: " + email + ", decodedEmail: " + decodedEmail);

        return person.get();
    }

    @DeleteMapping("/people/{id}")
    public void deletePerson(@PathVariable long id) {
        personRepository.deleteById(id);
    }

    @PostMapping("/people")
    public ResponseEntity<Object> createPerson(@RequestBody Person person) {
        Person savedPerson = personRepository.save(person);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPerson.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Object> updatePerson(@RequestBody Person person, @PathVariable long id) {

        Optional<Person> personOptional = personRepository.findById(id);

        if (!personOptional.isPresent())
            return ResponseEntity.notFound().build();

        person.setId(id);

        personRepository.save(person);

        return ResponseEntity.noContent().build();
    }

}
