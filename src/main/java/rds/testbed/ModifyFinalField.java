package rds.testbed;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/18/11
 * Time: 9:06 AM
 */
public class ModifyFinalField {
    public static void main(String[] args) throws Exception {
        IHaveAFinalField iHaveAFinalField = new IHaveAFinalField(10);
        System.out.println(iHaveAFinalField.x);
        Field field = ReflectionUtils.findField(IHaveAFinalField.class, "x");
        ReflectionUtils.makeAccessible(field);
        field.set(iHaveAFinalField, 15);
        System.out.println(iHaveAFinalField.x);
    }
}

class IHaveAFinalField {
    public final int x;

    IHaveAFinalField(int x) {
        this.x = x;
    }
}
