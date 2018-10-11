package com.tiket.tix.poc.validate.validate;

import com.tiket.tix.poc.validate.model.Person;
import org.springframework.stereotype.Component;

import static org.springframework.util.StringUtils.*;

/**
 * @author zakyalvan
 */
class PersonCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public boolean supports(Object type) {
        return type != null && Person.class.isAssignableFrom(type.getClass());
    }

    @Override
    public String build(Object object) {
        Person person = (Person) object;
        if(person.getContact() == null || (person.getContact() != null && !hasText(person.getContact().getEmailAddress()))) {
            return null;
        }
        return person.getContact().getEmailAddress();
    }
}
