package com.netcracker.minispring.writer;

public class JsonWriter implements Writer {
    @Override
    public void write(String str) {
        System.out.println("json: "+str);
    }
}
