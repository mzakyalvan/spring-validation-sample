package com.tiket.tix.poc.validate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author zakyalvan
 */
@Getter
@JsonDeserialize(builder = Person.PersonBuilder.class)
@SuppressWarnings("serial")
public class Person implements Serializable {
    @NotBlank
    private String firstName;

    private String lastName;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @NotNull
    private Gender gender;

    @Valid
    @NotNull
    private Contact contact;

    @Builder(builderClassName = "PersonBuilder")
    public Person(String firstName, String lastName, LocalDate dateOfBirth, Gender gender, Contact contact) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contact = contact;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class PersonBuilder {
        private LocalDate dateOfBirth;

        @JsonFormat(pattern = "yyyy-MM-dd")
        public PersonBuilder dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }
    }
}
