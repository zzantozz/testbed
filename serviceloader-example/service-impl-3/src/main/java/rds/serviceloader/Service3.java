package rds.serviceloader;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 8/8/11
 * Time: 10:47 PM
 */
public class Service3 implements MyService {
    @Override
    public void sayHi() {
        System.out.println("Hi from 3");
    }
}
