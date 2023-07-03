package com.library;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Application {

    private static final Integer PORT = 8080;

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(PORT);
        tomcat.addWebapp("", new File("src").getAbsolutePath());
        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();
    }

}
