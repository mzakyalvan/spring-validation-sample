package com.tiket.tix.poc.validate.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author zakyalvan
 */
@Getter
@JsonDeserialize(builder = Contact.ContactBuilder.class)
@SuppressWarnings("serial")
public class Contact implements Serializable {
    @Email
    @NotBlank
    private String emailAddress;

    @NotBlank
    private String phoneNumber;

    @Builder
    protected Contact(String emailAddress, String phoneNumber) {
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static class ContactBuilder {

    }
}
