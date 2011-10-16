package rds.aspectj;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 4/16/11
 * Time: 5:42 PM
 */
@Aspect
public class AnAspect {
    @After("execution(public static void App.main(..))")
    public void talk() {
        System.out.println("hi there");
    }
}
