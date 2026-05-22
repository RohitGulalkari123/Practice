package com.core.capgimini.streams.javafeatures.customAnnotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AdminOnly {

    /**
     * @Retention
     *
     * Defines how long annotation lives.
     *
     *@Target
     * Defines where annotation applies
     *
     *
     *
     * **/
}
