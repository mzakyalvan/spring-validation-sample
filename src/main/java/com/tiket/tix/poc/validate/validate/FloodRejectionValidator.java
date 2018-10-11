package com.tiket.tix.poc.validate.validate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.*;

/**
 * @author zakyalvan
 */
@Slf4j
@Component
public class FloodRejectionValidator implements ConstraintValidator<RejectFlood, Object> {
    /**
     * Simplified cache.
     */
    private final Map<String, Object> cachedValues = new HashMap<>();

    private List<CacheKeyBuilder> cacheKeyBuilders = Collections.singletonList(new PersonCacheKeyBuilder());

    private boolean rejectionEnabled;

    @Override
    public void initialize(RejectFlood constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null) {
            // When can't handle, assume value is valid.
            log.info("Given object is null");
            return true;
        }

        log.info("Building cache key for object type {}", value.getClass().getName());
        CacheKeyBuilder usedBuilder = null;
        for(CacheKeyBuilder builder : cacheKeyBuilders) {
            log.info("Evaluating whether cache key builder {} supporting {}", builder.getClass().getName(), value.getClass().getName());
            if(builder.supports(value)) {
                log.info("Cache key builder {} support {}", builder.getClass().getName(), value.getClass().getName());
                usedBuilder = builder;
                break;
            }
        }

        if(usedBuilder == null) {
            log.warn("No cache key builder object for given value type {}", value.getClass().getName());
            // When can't handle, assume value is valid.
            return true;
        }

        String cacheKey = usedBuilder.build(value);
        if(!StringUtils.hasText(cacheKey)) {
            log.info("Built cache key is null or empty text");
            return true;
        }

        log.info("Cache key built : {}", cacheKey);
        if(!cachedValues.containsKey(cacheKey)) {
            cachedValues.put(cacheKey, value);
            return true;
        }

        log.error("Cache key already exists, reject...");
        return false;
    }
}
