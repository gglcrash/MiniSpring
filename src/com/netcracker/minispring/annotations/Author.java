package com.netcracker.minispring.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Author {
    String firstName() default "none";
    String lastName() default "none";
}
