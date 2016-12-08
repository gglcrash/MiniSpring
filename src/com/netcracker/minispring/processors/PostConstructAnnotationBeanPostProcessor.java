package com.netcracker.minispring.processors;

import com.netcracker.minispring.annotations.PostConstruct;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PostConstructAnnotationBeanPostProcessor implements BeanPostProcessor{
    @Override
    public void checkAnnotation(Class<?> cl, Object instance) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        Method[] methods = cl.getDeclaredMethods();
        for (Method method: methods
                ) {
            if(method.isAnnotationPresent(PostConstruct.class)&&method.getParameterCount()==0) {
                method.setAccessible(true);
                method.invoke(instance);
            }
        }
    }
}
