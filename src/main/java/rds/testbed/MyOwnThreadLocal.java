package rds.testbed;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 7/13/11
 * Time: 4:26 PM
 */
public class MyOwnThreadLocal extends ThreadLocal {
    private ThreadLocal t = new ThreadLocal() {
        @Override
        protected Object initialValue() {
            return null;
        }
    };
}
