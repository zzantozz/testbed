package rds.testbed;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/18/11
 * Time: 9:41 AM
 */
public class ModifyStringInstance {
    public static void main(String[] args) throws Exception {
        String foo = "foo";
        System.out.println("foo's hash value: " + foo.hashCode());
        Field stringValueField = String.class.getDeclaredField("value");
        stringValueField.setAccessible(true);
        stringValueField.set(foo, "bar".toCharArray());
        Field stringHashField = String.class.getDeclaredField("hash");
        stringHashField.setAccessible(true);
        stringHashField.set(foo, 0);
        System.out.println("foo's new value: " + foo);
        System.out.println("foo's new hash value: " + foo.hashCode());
    }
}
