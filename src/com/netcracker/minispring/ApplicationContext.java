package com.netcracker.minispring;

import com.netcracker.minispring.annotations.Component;
import com.netcracker.minispring.processors.*;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ApplicationContext {
    private ArrayList<BeanPostProcessor> analyzers = new ArrayList<>();
    private java.util.Properties properties;
    private final Map<String,Class<?>> components;
    private static ApplicationContext instance;
    static
    {
        instance = new ApplicationContext();
    }
    private ApplicationContext() {

        properties = new java.util.Properties();
        components =  ClassFinder.getClassesFromPackage(new File("out\\production\\MiniSpring\\"),"", Component.class);
        try {
            properties.load(new FileInputStream("config.properties"));
        }
        catch (Exception e) {
            e.printStackTrace();

        }
        analyzers.add(new AutoInjectAnnotationBeanPostProcessor(properties));
        analyzers.add(new AutowiredAnnotationBeanPostProcessor());
        analyzers.add(new InjectRandomIntAnnotationBeanPostProcessor());
        analyzers.add(new PostConstructAnnotationBeanPostProcessor());
        //analyzers.add(new AutoInjectAnnotationBeanPostProcessor(new Properties()));
    }

    public static ApplicationContext getInstance() {
        return instance;
    }

    public Object getBean(String componentName) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {

        return getBean(components.get(componentName));
    }

    public Object getBean(Class<?> clazz) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {

        Object instance = clazz.newInstance();

        for (BeanPostProcessor analyzer : analyzers) {
            analyzer.checkAnnotation(instance.getClass(), instance);
        }
        return instance;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Map<String,Class<?>> getComponents() {
        return (Map<String,Class<?>>)((HashMap<String,Class<?>>)components).clone();
    }
}