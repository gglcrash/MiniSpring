package com.netcracker.minispring.processors;

import com.netcracker.minispring.ApplicationContext;
import com.netcracker.minispring.annotations.AutoInject;
import com.netcracker.minispring.annotations.Autowired;
import com.netcracker.minispring.annotations.Qualifier;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {
    public AutowiredAnnotationBeanPostProcessor() { }
    @Override
    public void analyze(Class<?> cl, Object instance) throws IllegalAccessException, InstantiationException, ClassNotFoundException, InvocationTargetException {
        Method[] methods = cl.getDeclaredMethods();
        Field[] fields = cl.getDeclaredFields();
        Constructor<?>[] constructors = cl.getDeclaredConstructors();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Autowired.class)) {

                int modifiers = method.getModifiers();
                method.setAccessible(true);

                ArrayList<Object> params = new ArrayList<>();

                for (Class<?> type : method.getParameterTypes()) {

                    if(type.isAnnotationPresent(Qualifier.class)) {
                        Qualifier qualifier = type.getAnnotation(Qualifier.class);
                        if(qualifier.name().equals(""))
                            params.add(ApplicationContext.getInstance().getBean(qualifier.clazz()));
                        else
                            params.add(ApplicationContext.getInstance().getBean(qualifier.name()));
                    }
                    else
                    if (ApplicationContext.getInstance().getComponents().containsValue(type)) {
                        if (!type.isInterface()) {
                            params.add(ApplicationContext.getInstance().getBean(type));
                        } else {
                            for (Object entry : ((HashMap) ApplicationContext.getInstance().getComponents()).values()) {
                                if (!((Class<?>) entry).isInterface() && type.isAssignableFrom(((Class<?>) entry))) {
                                    params.add(ApplicationContext.getInstance().getBean((Class<?>) entry));
                                    break;
                                }
                            }
                        }

                    }
                }
                method.invoke(instance,params.toArray());

                if(Modifier.isPrivate(modifiers)||Modifier.isProtected(modifiers))
                    method.setAccessible(false);
            }
        }
        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInject.class)) {

                int modifiers = field.getModifiers();
                field.setAccessible(true);
                if(field.isAnnotationPresent(Qualifier.class)) {
                    Qualifier qualifier = field.getAnnotation(Qualifier.class);
                    if(qualifier.name().equals(""))
                        field.set(instance,ApplicationContext.getInstance().getBean(qualifier.clazz()));
                    else
                        field.set(instance,ApplicationContext.getInstance().getBean(qualifier.name()));
                }
                else
                if (!field.getType().isInterface()) field.set(instance, ApplicationContext.getInstance().getBean(field.getType()));
                else
                {
                    for (Object entry:((HashMap)ApplicationContext.getInstance().getComponents()).values()) {
                        if(!((Class<?>)entry).isInterface()&&field.getType().isAssignableFrom(((Class<?>)entry))) {
                            field.set(instance,ApplicationContext.getInstance().getBean((Class<?>) entry));
                            break;
                        }
                    }
                }

                if(Modifier.isPrivate(modifiers)||Modifier.isProtected(modifiers))
                    field.setAccessible(false);
            }

        }

        for (Constructor<?> constructor : constructors) {
            if (constructor.isAnnotationPresent(AutoInject.class)) {
                ArrayList<Object> params = new ArrayList<>();
                for (Class<?> type : constructor.getParameterTypes()) {
                    if (ApplicationContext.getInstance().getComponents().containsValue(type))
                        params.add(ApplicationContext.getInstance().getBean(type));
                }
                instance = constructor.newInstance(params.toArray());

            }
        }
    }
}
