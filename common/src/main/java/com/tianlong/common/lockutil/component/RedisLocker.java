package com.tianlong.common.lockutil.component;

import com.tianlong.common.exception.UnableToAquireLockException;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisLocker implements DistributedLocker{
    private final static String LOCKER_PREFIX = "lock:";

    @Autowired
    RedissonConnector redissonConnector;
    @Override
    public <T> T lock(String resourceName, AquiredLockWorker<T> worker) throws Exception {
        return lock(resourceName, worker, 100);
    }

    @Override
    public <T> T lock(String resourceName, AquiredLockWorker<T> worker, int lockTime) throws Exception {
            RedissonClient redisson= redissonConnector.getClient();
            RLock lock = redisson.getLock(LOCKER_PREFIX + resourceName);
        boolean success = lock.tryLock(100, lockTime, TimeUnit.SECONDS);
        if (success) {
            try {
                return worker.invokeAfterLockAquire();
            }  finally {
                lock.unlock();
            }
        }
        throw new UnableToAquireLockException();
        }
}
