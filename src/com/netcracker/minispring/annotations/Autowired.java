package com.netcracker.minispring.annotations;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Target({CONSTRUCTOR, METHOD, PARAMETER, FIELD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {

    boolean required() default true;

}