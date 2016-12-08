package com.netcracker.minispring;

import com.netcracker.minispring.annotations.AutoInject;
import com.netcracker.minispring.annotations.Autowired;
import com.netcracker.minispring.annotations.Component;
import com.netcracker.minispring.writer.HtmlWriter;
import com.netcracker.minispring.writer.Writer;
import com.netcracker.minispring.writer.XmlWriter;

import java.lang.reflect.InvocationTargetException;

@Component("Example")
public class Example {
    Example (){ }

    private HtmlWriter htmlWriter;

    @Autowired
    void setHtmlWriter(HtmlWriter htmlWriter){
        this.htmlWriter = htmlWriter;
    }

    HtmlWriter getHtmlWriter(){
        return htmlWriter;
    }

    private XmlWriter xmlWriter;

    @AutoInject("XML")
    void setXmlWriter(XmlWriter writer){
        this.xmlWriter = writer;
    }

    XmlWriter getXmlWriter(){
        return  xmlWriter;
    }

    public void start(){
        //Component works
        checkComponent();

        //autowired works
        checkAutowired();

        //autoinject works
        checkAutoInject();
    }
    void checkComponent(){
        Writer myWriter = null;

        try {
            myWriter = (Writer)ApplicationContext.getInstance().getBean("JSON");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        finally {
            if (myWriter != null) {
                myWriter.write("Hello World! Component works!");
            }
        }
    }

    void checkAutowired(){
        try {
            getHtmlWriter().write("Autowired works!");
        }
        catch (NullPointerException e){
            System.out.println("XmlWriter Autowired was required false!");
        }
    }

    void checkAutoInject(){
        try {
            getXmlWriter().write("Auoinject for XmlWriter works!");
        }
        catch (NullPointerException e){
            System.out.println("XmlWriter2 AutoInject does not works!");
        }
    }
}

