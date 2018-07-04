# README

This is sample of basic validation support in spring, to simplify spring boot.

## Controller ```@RequestBody``` Parameter Validation

Look into ```com.tiket.tix.poc.validate.controller.PeopleController``` and its tests in ```com.tiket.tix.poc.validate.controller.PeopleControllerTests```.

> Important to note, beside ```@org.springframework.validation.annotation.Validated``` annotation, we have to check violation via ```org.springframework.validation.BindingResult``` object, which declared next to ```@RequestBody``` parameter.

## Service Method Parameter Validation

Look into ```com.tiket.tix.poc.validate.service.RegistrationService``` and its tests in ```com.tiket.tix.poc.validate.service.RegistrationServiceTests```.

Also look into ```com.tiket.tix.poc.validate.SampleApplication```, to enable method parameter validation, a bean of type ```org.springframework.validation.beanvalidation.MethodValidationPostProcessor``` declared there

```java

@SpringBootApplication
public class SampleApplication {
    // ... Omitted ...
    
    @Bean
    MethodValidationPostProcessor methodValidationEnabler() {
        return new MethodValidationPostProcessor();
    }
}

```
