package rds.testbed;

import java.net.URL;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/15/11
 * Time: 10:50 PM
 */
public class LoadSelfAsResource {
    public static void main(String[] args) {
        System.out.println(LoadSelfAsResource.class.getProtectionDomain().getCodeSource().getLocation());
        String name = LoadSelfAsResource.class.getName().replace('.', '/') + ".class";
        System.out.println("Name=" + name);
        System.out.println("Load via class loader");
        URL url = LoadSelfAsResource.class.getClassLoader().getResource(name);
        System.out.println("Resource=" + url);
        System.out.println("Load as system resource");
        url = ClassLoader.getSystemResource(name);
        System.out.println("Resource=" + url);
        name = "/" + LoadSelfAsResource.class.getName().replace('.', '/') + ".class";
        System.out.println("Name=" + name);
        System.out.println("Load via class");
        url = LoadSelfAsResource.class.getResource(name);
        System.out.println("Resource=" + url);
        System.out.println("Load via class loader");
        url = LoadSelfAsResource.class.getClassLoader().getResource(name);
        System.out.println("Resource=" + url);
        System.out.println("Load as system resource");
        url = ClassLoader.getSystemResource(name);
        System.out.println("Resource=" + url);
        System.out.format("a d:%d; an f: %f", 1, 1.5);
    }
}
