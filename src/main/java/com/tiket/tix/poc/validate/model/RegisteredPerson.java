package com.tiket.tix.poc.validate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author zakyalvan
 */
@SuppressWarnings("serial")
public class RegisteredPerson extends Person {
    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime registeredTime;

    @Builder(builderMethodName = "registeredPerson")
    public RegisteredPerson(String id, String firstName, String lastName, LocalDate dateOfBirth, Gender gender, Contact contact, LocalDateTime registeredTime) {
        super(firstName, lastName, dateOfBirth, gender, contact);
        this.id = id;
        this.registeredTime = registeredTime;
    }
}
