package com.netcracker.minispring.writer;

public class XmlWriter implements Writer {
    @Override
    public void write(String str) {
        System.out.println("xml: "+str);
    }
}
