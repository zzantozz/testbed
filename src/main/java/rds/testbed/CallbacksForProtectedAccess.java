package rds.testbed;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/14/11
 * Time: 2:27 PM
 */
public class CallbacksForProtectedAccess {

}

class Dad {
    public void doSomething() {}

    protected void foo() {}

    protected void bar() {}
}

class Son extends Dad {
    protected void one() {}

    protected void two() {}
}

class DadSubclass extends Dad {
    @Override
    public void doSomething() {
        Helper.helpMe(new Callback() {
            public void foo() {
                DadSubclass.this.foo();
            }

            public void bar() {
                DadSubclass.this.bar();
            }

            public void one() {
                throw new UnsupportedOperationException("one() doesn't exist in Dad");
            }

            public void two() {
                throw new UnsupportedOperationException("two() doesn't exist in Dad");
            }
        });
    }
}

class SonSubclass extends Son {
    @Override
    public void doSomething() {
        Helper.helpMe(new Callback() {
            public void foo() {
                SonSubclass.this.foo();
            }

            public void bar() {
                SonSubclass.this.bar();
            }

            public void one() {
                SonSubclass.this.one();
            }

            public void two() {
                SonSubclass.this.two();
            }
        });
    }
}

interface Callback {
    void foo();
    void bar();
    void one();
    void two();
}
class Helper {
    static void helpMe(Callback callback) {
        // My code
    }
}