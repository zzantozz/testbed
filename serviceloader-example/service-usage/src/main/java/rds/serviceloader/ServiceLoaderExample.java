package rds.serviceloader;

import java.util.ServiceLoader;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 8/8/11
 * Time: 10:47 PM
 */
public class ServiceLoaderExample {
    public static void main(String[] args) {
        ServiceLoader<MyService> loader = ServiceLoader.load(MyService.class);
        for (MyService myService : loader) {
            myService.sayHi();
        }
    }
}
