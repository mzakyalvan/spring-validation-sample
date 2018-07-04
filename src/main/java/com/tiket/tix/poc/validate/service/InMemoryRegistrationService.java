package com.tiket.tix.poc.validate.service;

import com.tiket.tix.poc.validate.model.Person;
import com.tiket.tix.poc.validate.model.RegisteredPerson;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zakyalvan
 */
@Service
@Validated
class InMemoryRegistrationService implements RegistrationService {
    private final List<RegisteredPerson> registry = new ArrayList<>();

    @Override
    public Person registerPerson(Person person) {
        RegisteredPerson registered = RegisteredPerson.registeredPerson()
                .firstName(person.getFirstName()).lastName(person.getLastName())
                .dateOfBirth(person.getDateOfBirth())
                .gender(person.getGender())
                .contact(person.getContact())
                .build();

        registry.add(registered);
        return registered;
    }

    @Override
    public boolean emailRegistered(String emailAddress) {
        return registry.stream().map(registered -> registered.getContact())
                .anyMatch(contact -> contact.getEmailAddress().equalsIgnoreCase(emailAddress));
    }
}
