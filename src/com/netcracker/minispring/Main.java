package com.netcracker.minispring;

import com.netcracker.minispring.writer.Writer;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) {
        Writer myWriter = null;
        //Component works
        try {
            myWriter = (Writer)ApplicationContext.getInstance().getBean("HTML");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        myWriter.write("Hello World!");
    }
}
