package com.netcracker.minispring.writer;

import com.netcracker.minispring.annotations.Component;

@Component("JSON")
public class JsonWriter implements Writer {
    @Override
    public void write(String str) {
        System.out.println("json: "+str);
    }
}
