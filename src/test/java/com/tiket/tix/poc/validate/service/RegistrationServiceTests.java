package com.tiket.tix.poc.validate.service;

import com.tiket.tix.poc.validate.SampleApplication;
import com.tiket.tix.poc.validate.model.Contact;
import com.tiket.tix.poc.validate.model.Gender;
import com.tiket.tix.poc.validate.model.Person;
import com.tiket.tix.poc.validate.model.RegisteredPerson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.IsInstanceOf.*;

/**
 * @author zakyalvan
 */
@SpringBootTest(classes = SampleApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class RegistrationServiceTests {
    @Autowired
    private RegistrationService registrationService;

    @Test(expected = ConstraintViolationException.class)
    public void givenNullEmailAddress_whenInquiryRegistration_thenMustThrowConstraintViolationError() {
        registrationService.emailRegistered(null);
    }

    @Test
    @DirtiesContext
    public void givenValidPersonData_whenRegistering_thenMustReturnRegisteredPerson() {
        Person incomplete = Person.builder()
                .firstName("Zaky")
                .lastName("Alvan")
                .gender(Gender.MALE)
                .dateOfBirth(LocalDate.of(1985, Month.JUNE, 18))
                .contact(Contact.builder()
                        .emailAddress("zaky.alvan@tiket.com")
                        .phoneNumber("081320144088")
                        .build())
                .build();

        Person person = registrationService.registerPerson(incomplete);
        assertThat(person, instanceOf(RegisteredPerson.class));
    }

    @Test(expected = ConstraintViolationException.class)
    public void givenIncompletePersonData_whenRegistering_thenMustThrowConstraintViolationError() {
        Person incomplete = Person.builder()
                .lastName("Alvan")
                .gender(Gender.MALE)
                .dateOfBirth(LocalDate.of(1985, Month.JUNE, 18))
                .contact(Contact.builder()
                        .phoneNumber("081320144088")
                        .build())
                .build();

        registrationService.registerPerson(incomplete);
    }

    @Test(expected = ConstraintViolationException.class)
    public void givenInvalidEmailAddressOfPersonData_whenRegistering_thenMustThrowConstraintViolationError() {
        Person incomplete = Person.builder()
                .firstName("Zaky")
                .lastName("Alvan")
                .gender(Gender.MALE)
                .dateOfBirth(LocalDate.of(1985, Month.JUNE, 18))
                .contact(Contact.builder()
                        .emailAddress("zaky.alvan[at]tiket.com")
                        .phoneNumber("081320144088")
                        .build())
                .build();

        registrationService.registerPerson(incomplete);
    }
}
