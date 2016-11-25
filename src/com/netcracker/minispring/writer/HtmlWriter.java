package com.netcracker.minispring.writer;

public class HtmlWriter implements Writer {
    @Override
    public void write(String str) {
        System.out.println("<h1>"+str+"</h1>");
    }
}
