package com.tiket.tix.poc.validate.controller;

import com.tiket.tix.poc.validate.model.Person;
import com.tiket.tix.poc.validate.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author zakyalvan
 */
@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    ResponseEntity<Person> registerPerson(@Validated @RequestBody Person person, BindingResult bindings) {
        if(!bindings.hasFieldErrors("contact.emailAddress") &&
                registrationService.emailRegistered(person.getContact().getEmailAddress())) {
            bindings.rejectValue("contact.emailAddress", "contact.emailAddress.alreadyRegistered");
        }

        if(bindings.hasErrors()) {
            /**
             * You can also throw a custom data binding exception, which would be handled in error controller.
             */
            return ResponseEntity.badRequest().build();
        }

        Person registered = registrationService.registerPerson(person);
        return ResponseEntity.ok(registered);
    }
}
