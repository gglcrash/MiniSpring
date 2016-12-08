package com.netcracker.minispring;

import com.netcracker.minispring.annotations.Component;
import com.netcracker.minispring.processors.AutoInjectAnnotationBeanPostProcessor;
import com.netcracker.minispring.processors.AutowiredAnnotationBeanPostProcessor;
import com.netcracker.minispring.processors.BeanPostProcessor;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ApplicationContext {
    private ArrayList<BeanPostProcessor> analyzers = new ArrayList<BeanPostProcessor>();
    private java.util.Properties properties;
    private final Map<String,Class<?>> components;
    private static ApplicationContext instance;
    static
    {
        instance = new ApplicationContext();
    }
    private ApplicationContext() {


        components =  ClassFinder.getClassesFromPackage(new File("out\\production\\MiniSpring\\"),"", Component.class);
        //analyzers.add(new InjectAnalyzer(properties));
        analyzers.add(new AutowiredAnnotationBeanPostProcessor());
        analyzers.add(new AutoInjectAnnotationBeanPostProcessor());
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
            analyzer.analyze(instance.getClass(), instance);
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