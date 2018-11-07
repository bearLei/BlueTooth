package xzzb.com.bluetoothdemo.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lei on 18年11月5日
 */

public class LockManager {


    private static LockManager instance;

    private Map<Class, LockObject> lockObjectMap;

    private LockManager() {
        lockObjectMap = new HashMap<>();
    }

    public static LockManager getInstance() {
        if (instance == null) {
            synchronized (LockManager.class) {
                instance = new LockManager();
            }
        }
        return instance;
    }


    public void _wait(Class clazz, int timeout) {
        LockObject lockObject = lockObjectMap.get(clazz);
        if (lockObject == null) {
            lockObject = new LockObject();
            lockObjectMap.put(clazz, lockObject);
        }
        lockObject._wait(timeout);
    }

    public void _notify(Class clazz) {
        LockObject lockObject = lockObjectMap.get(clazz);
        if(lockObject == null) {
            return;
        }
        lockObject._notify();
        lockObjectMap.remove(clazz);
    }

    private class LockObject {
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();

        public void _wait(int timeout) {
            try {
                lock.lock();
                condition.await(timeout, TimeUnit.SECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        public void _notify() {
            try {
                lock.lock();
                condition.signalAll();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }


}
