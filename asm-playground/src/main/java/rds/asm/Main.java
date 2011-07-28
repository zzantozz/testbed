package rds.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.TraceClassVisitor;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/27/11
 * Time: 4:35 PM
 */
public class Main {
    public static void main(String[] args) throws Exception {
        Class<TestPojo> normalClassLoaderClass = TestPojo.class;
        MakeItImmutableClassLoader classLoader = new MakeItImmutableClassLoader(Main.class.getClassLoader());
        Class<?> clazz = classLoader.findClass("rds.asm.TestPojo");
        System.out.println(normalClassLoaderClass.getClassLoader());
        System.out.println(clazz.getClassLoader());
        if (clazz.equals(normalClassLoaderClass)) {
            throw new IllegalStateException("Didn't get the modified class instance");
        }
        Constructor<?> constructor = clazz.getConstructor(Integer.TYPE, Integer.TYPE);
        Object o = constructor.newInstance(5, 10);
        System.out.println(o);
        BeanInfo beanInfo = Introspector.getBeanInfo(o.getClass());
        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            if (propertyDescriptor.getName().equals("x")) {
                propertyDescriptor.getWriteMethod().invoke(o, 42);
            }
        }
        System.out.println(o);
        System.out.println(o.getClass().equals(clazz));
    }
}
