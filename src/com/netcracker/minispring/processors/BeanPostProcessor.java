package com.netcracker.minispring.processors;

import java.lang.reflect.InvocationTargetException;

public interface BeanPostProcessor {
    void analyze(Class<?> cl,Object instance) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException;
}
