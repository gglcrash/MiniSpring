package com.netcracker.minispring.processors;

public interface BeanPostProcessor {
    void analyze(Class<?> cl,Object instance) throws IllegalAccessException, InstantiationException, ClassNotFoundException;
}