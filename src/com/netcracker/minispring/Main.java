package com.netcracker.minispring;

import com.netcracker.minispring.annotations.AutoInject;
import com.netcracker.minispring.annotations.Autowired;
import com.netcracker.minispring.writer.HtmlWriter;
import com.netcracker.minispring.writer.Writer;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) {
        try {
            Example example = (Example) ApplicationContext.getInstance().getBean("Example");
            example.start();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
