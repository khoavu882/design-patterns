import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Khoa Vu.
 * Mail: khoavd12@fpt.com
 * Date: 3/17/25
 * Time: 4:49 PM
 */

public class ReentrantLockSafe {

    private static final Lock lock = new ReentrantLock();
    private static ReentrantLockSafe instance;

    private ReentrantLockSafe() {}

    public static ReentrantLockSafe getInstance() {
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ReentrantLockSafe();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

}
