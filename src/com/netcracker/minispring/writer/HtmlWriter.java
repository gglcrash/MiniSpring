package com.netcracker.minispring.writer;

import com.netcracker.minispring.annotations.Component;

@Component
public class HtmlWriter implements Writer {
    @Override
    public void write(String str) {
        System.out.println("<h1>"+str+"</h1>");
    }
}
