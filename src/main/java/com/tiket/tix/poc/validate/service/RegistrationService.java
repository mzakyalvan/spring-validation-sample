package com.tiket.tix.poc.validate.service;

import com.tiket.tix.poc.validate.model.Person;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author zakyalvan
 */
public interface RegistrationService {
    Person registerPerson(@Valid @NotNull Person person);
    boolean emailRegistered(@NotBlank String emailAddress);
}
