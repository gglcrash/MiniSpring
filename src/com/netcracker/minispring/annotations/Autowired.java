package com.netcracker.minispring.annotations;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({CONSTRUCTOR, METHOD, PARAMETER, FIELD, ANNOTATION_TYPE})
@Retention(value=RUNTIME)
public @interface Autowired {
    boolean required() default true;
}