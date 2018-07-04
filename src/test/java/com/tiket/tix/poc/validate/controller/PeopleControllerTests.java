package com.tiket.tix.poc.validate.controller;

import com.tiket.tix.poc.validate.SampleApplication;
import com.tiket.tix.poc.validate.model.Contact;
import com.tiket.tix.poc.validate.model.Gender;
import com.tiket.tix.poc.validate.model.Person;
import com.tiket.tix.poc.validate.service.RegistrationService;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.time.Month;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * @author zakyalvamn
 */
@SpringBootTest(classes = SampleApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class PeopleControllerTests {
    @Autowired
    private RegistrationService registrationService;

    @LocalServerPort
    private int randomPort;

    @Before
    public void setup() {
        baseURI = UriComponentsBuilder.newInstance()
                .scheme("http").host("localhost").port(randomPort)
                .build().toUriString();
    }

    @Test
    @DirtiesContext
    public void givenValidPersonData_whenRegistering_mustReturnSuccess() throws Exception {
        Resource validPerson = new ClassPathResource("json/valid.person.json");

        given().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).body(validPerson.getFile()).log().all(true).post("/people")
                .then().log().all(true).assertThat().statusCode(200)
                    .body("firstName", equalTo("Zaky"))
                    .body("lastName", equalTo("Alvan"))
                    .body("gender", equalTo("MALE"))
                    .body("dateOfBirth", equalTo("1985-06-18"))
                    .body("contact.emailAddress", equalTo("zaky.alvan@tiket.com"))
                    .body("contact.phoneNumber", equalTo("081320144088"));
    }

    @Test
    @DirtiesContext
    public void givenPersonDataWithMissingProperties_whenRegistering_mustReturnBadRequest() throws Exception {
        Resource validPerson = new ClassPathResource("json/invalid.person.json");

        given().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).body(validPerson.getFile()).log().all(true).post("/people")
                .then().log().all(true).assertThat().statusCode(400);
    }

    @Test
    @DirtiesContext
    public void givenPersonDataWithRegisteredEmail_whenRegistering_mustReturnBadRequest() throws Exception {
        registrationService.registerPerson(Person.builder()
                .firstName("Zaky").lastName("Alvan")
                .gender(Gender.MALE)
                .dateOfBirth(LocalDate.of(1985, Month.JUNE, 18))
                .contact(Contact.builder()
                        .emailAddress("zaky.alvan@tiket.com")
                        .phoneNumber("081320144088")
                        .build())
                .build());

        Resource validPerson = new ClassPathResource("json/valid.person.json");

        given().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).body(validPerson.getFile()).log().all(true).post("/people")
                .then().log().all(true).assertThat().statusCode(400);
    }
}
