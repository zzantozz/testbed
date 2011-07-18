package rds.testbed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/17/11
 * Time: 9:44 AM
 */
    @RunWith(Parameterized.class)
    public class InterfaceTesting {
        public MyInterface myInterface;

        public InterfaceTesting(MyInterface myInterface) {
            this.myInterface = myInterface;
        }

        @Test
        public final void testMyMethod_True() {
            assertTrue(myInterface.myMethod(true));
        }

        @Test
        public final void testMyMethod_False() {
            assertFalse(myInterface.myMethod(false));
        }

        @Parameterized.Parameters
        public static Collection<Object[]> instancesToTest() {
            return Arrays.asList(
                        new Object[]{new MyClass1()},
                        new Object[]{new MyClass2()}
            );
        }
    }

interface MyInterface {
    /**
     * Return the given value.
     */
    public boolean myMethod(boolean retVal);
}

class MyClass1 implements MyInterface {
    public boolean myMethod(boolean retVal) {
        return retVal;
    }
}

class MyClass2 implements MyInterface {
    public boolean myMethod(boolean retVal) {
        return retVal;
    }
}

