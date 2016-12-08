package com.netcracker.minispring.processors;


import com.netcracker.minispring.annotations.AutoInject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Properties;

public class AutoInjectAnnotationBeanPostProcessor implements  BeanPostProcessor {

    private Properties props;
    public AutoInjectAnnotationBeanPostProcessor(Properties props){
        this.props = props;
    }
    @Override
    public void checkAnnotation(Class<?> cl, Object instance) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        Method[] methods = cl.getDeclaredMethods();
        Field[] fields = cl.getDeclaredFields();
        Constructor<?>[] constructors = cl.getDeclaredConstructors();

        for (Method method: methods ) {
            if (method.isAnnotationPresent(AutoInject.class)) {
                method.setAccessible(true);
                method.invoke(instance, Class.forName(props.getProperty(method.getAnnotation(AutoInject.class).value())).newInstance());
            }
        }
        for (Field field:fields ) {
            if (field.isAnnotationPresent(AutoInject.class)) {
                field.set(instance, Class.forName(props.getProperty(field.getAnnotation(AutoInject.class).value())).newInstance());
            }
        }
        for (Constructor<?> contructor:constructors ) {
            if(contructor.isAnnotationPresent(AutoInject.class)) {
                instance = contructor.newInstance(Class.forName(props.getProperty(contructor.getAnnotation(AutoInject.class).value())).newInstance());
            }

        }
    }
}
