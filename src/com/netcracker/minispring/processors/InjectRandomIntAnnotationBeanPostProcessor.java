package com.netcracker.minispring.processors;

import com.netcracker.minispring.annotations.InjectRandomInt;
import com.sun.deploy.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class InjectRandomIntAnnotationBeanPostProcessor implements BeanPostProcessor {
    @Override
    public void checkAnnotation(Class<?> cl, Object instance) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        Field[] fields = cl.getDeclaredFields();
        for(Field field : fields){
            InjectRandomInt annotation = field.getAnnotation(InjectRandomInt.class);
            if(annotation!=null){
                int min = annotation.min();
                int max = annotation.max();
                Random random = new Random();
                int rand = min + random.nextInt(max-min);
                field.setAccessible(true);
                field.setInt(instance,rand);
            }
        }
    }
}
