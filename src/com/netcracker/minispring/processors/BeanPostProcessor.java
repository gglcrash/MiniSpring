package com.netcracker.minispring.processors;

import java.lang.reflect.InvocationTargetException;

public interface BeanPostProcessor {
    void checkAnnotation(Class<?> cl, Object instance) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException;
}
