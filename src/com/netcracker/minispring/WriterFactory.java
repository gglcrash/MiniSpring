package com.netcracker.minispring;

import com.netcracker.minispring.writer.Writer;

import java.io.*;
import java.util.Properties;

public class WriterFactory {
    private static WriterFactory instance;
    Properties prop = new Properties();
    OutputStream output = null;
    InputStream input = null;
    Object classWriter = null;

    private WriterFactory(){}

    public static WriterFactory getInstance(){
        if(instance==null){
            instance = new WriterFactory();
        }
        return instance;
    }

    public Writer getWriter(String type){

        try {
            input = new FileInputStream("config.properties");
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String path = (String)prop.get(type);
        try {
            classWriter = Class.forName(path).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (Writer)classWriter;
    }
}
