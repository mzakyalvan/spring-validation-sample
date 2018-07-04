package com.tiket.tix.poc.validate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * @author zakyalvan
 */
@SpringBootApplication
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

    /**
     * Enable method validation.
     *
     * @return
     */
    @Bean
    MethodValidationPostProcessor methodValidationEnabler() {
        return new MethodValidationPostProcessor();
    }
}
