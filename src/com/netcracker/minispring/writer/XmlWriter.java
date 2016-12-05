package com.netcracker.minispring.writer;

import com.netcracker.minispring.annotations.Component;

@Component
public class XmlWriter implements Writer {
    @Override
    public void write(String str) {
        System.out.println("xml: "+str);
    }
}
