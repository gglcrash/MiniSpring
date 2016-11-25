package com.netcracker.minispring;

import com.netcracker.minispring.writer.Writer;

public class Main {

    public static void main(String[] args) {
        WriterFactory factory = WriterFactory.getInstance();
        Writer writer = factory.getWriter("HTML");
        writer.write("Hello World!");
    }
}
