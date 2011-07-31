package rds.asm;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/27/11
 * Time: 4:35 PM
 */
public class MakingFieldsFinalMain {
    public static void main(String[] args) throws Exception {
        MakeFieldsFinalClassLoader classLoader =
                new MakeFieldsFinalClassLoader(MakingFieldsFinalMain.class.getClassLoader());
        Class<?> clazz = classLoader.findClass("rds.asm.TestPojo");
        Constructor<?> constructor = clazz.getConstructor(Integer.TYPE);
        Object o = constructor.newInstance(5);
        System.out.println("The object we constructed: " + o);
        System.out.println("Object's class: " + o.getClass());
        System.out.println("Object's class equal to rds.asm.TestPojo.class? " +
                                   rds.asm.TestPojo.class.equals(o.getClass()));
        BeanInfo beanInfo = Introspector.getBeanInfo(o.getClass());
        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            if (propertyDescriptor.getName().equals("x")) {
                propertyDescriptor.getWriteMethod().invoke(o, 42);
            }
        }
        System.out.println("After invoking setX(42): " + o);
    }
}
