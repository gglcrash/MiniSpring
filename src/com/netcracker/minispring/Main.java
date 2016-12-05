package com.netcracker.minispring;

import com.netcracker.minispring.writer.Writer;

public class Main {

    public static void main(String[] args) {
        WriterFactory factory = WriterFactory.getInstance();
        Writer myWriter = factory.getWriter("XML");
        myWriter.write("Hello World!");
    }
}
