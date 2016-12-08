package com.netcracker.minispring.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, CONSTRUCTOR, FIELD,PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface AutoInject {
    String value();
}