package rds.springcaching;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: ryan
 * Date: 4/16/12
 * Time: 2:10 PM
 */
@Component
public class SomethingCacheableImpl implements SomethingCacheable {
    @Override
    @Cacheable("some cache region")
    public long getCurrentTime() {
        return getCurrentTimeWithoutCaching();
    }

    @Override
    public long getCurrentTimeWithoutCaching() {
        return System.nanoTime();
    }

    @Override
    @CacheEvict("some cache region")
    public void someEvictingOperation() {
    }
}
