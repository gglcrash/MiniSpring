package com.netcracker.minispring;

import com.netcracker.minispring.annotations.*;
import com.netcracker.minispring.writer.HtmlWriter;
import com.netcracker.minispring.writer.Writer;
import com.netcracker.minispring.writer.XmlWriter;

import java.lang.reflect.InvocationTargetException;

@Component("Example")
public class Example {
    Example (){ }

    private int postConstructValue;

    @PostConstruct
    void postConstructMethod(){
        postConstructValue = 500;
    }

    @InjectRandomInt(min = 5, max = 10)
    int random;
    private HtmlWriter htmlWriter;
    private XmlWriter xmlWriter;

    @Autowired
    void setHtmlWriter(HtmlWriter htmlWriter){
        this.htmlWriter = htmlWriter;
    }

    HtmlWriter getHtmlWriter(){
        return htmlWriter;
    }

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

        //check random int
        checkRandomInt();

        //checkPostConstruct
        checkPostConstruct();
    }

    private void checkComponent(){
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

    private void checkAutowired(){
        try {
            getHtmlWriter().write("Autowired works!");
        }
        catch (NullPointerException e){
            System.out.println("XmlWriter Autowired was required false!");
        }
    }

    private void checkAutoInject(){
        try {
            getXmlWriter().write("Auoinject for XmlWriter works!");
        }
        catch (NullPointerException e){
            System.out.println("XmlWriter2 AutoInject does not works!");
        }
    }

    private void checkRandomInt(){
        if (random!=0){
            System.out.println("Excelent! Random value: "+random);
        } else {
            System.out.println("Something goes wrong with random!");
        }
    }

    private void checkPostConstruct(){
        if (postConstructValue !=0){
            System.out.println("PostConstruct value: "+postConstructValue);
        } else {
            System.out.println("Something goes wrong with PostConstruct!");
        }
    }
}

