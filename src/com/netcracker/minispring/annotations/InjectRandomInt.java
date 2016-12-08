package com.netcracker.minispring.annotations;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(value=RUNTIME)
public @interface InjectRandomInt {
    int min();
    int max();
}
