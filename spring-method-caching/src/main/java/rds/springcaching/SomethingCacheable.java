package rds.springcaching;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 4/16/12
 * Time: 2:05 PM
 */
public interface SomethingCacheable {
    long getCurrentTime();

    long getCurrentTimeWithoutCaching();

    void someEvictingOperation();
}
