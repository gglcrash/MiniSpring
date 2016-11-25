package com.netcracker.minispring;

import com.netcracker.minispring.writer.Writer;

import java.util.Properties;

import static java.lang.Class.forName;

public class WriterFactory {
    private static WriterFactory instance;
    private WriterFactory(){}

    public static WriterFactory getInstance(){
        if(instance==null){
            instance = new WriterFactory();
        }
        return instance;
    }

    public Writer getWriter(String type){
        String path = Properties.get(type);
        try {
            Writer classWriter = Class.forName(path);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return classWriter;
    }
}
