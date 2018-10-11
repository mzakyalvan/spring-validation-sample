package com.tiket.tix.poc.validate.validate;

/**
 * Strategy object for building cache key.
 *
 * @author zakyalvan
 */
public interface CacheKeyBuilder {
    /**
     * Check whether type can build cache key for given object.
     *
     * @param object
     * @return
     */
    boolean supports(Object object);

    /**
     * Build the cache key for given object.
     *
     * @param object
     * @return
     */
    String build(Object object);
}
