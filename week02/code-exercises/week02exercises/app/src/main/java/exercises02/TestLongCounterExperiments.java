// For week 1
// sestoft@itu.dk * 2014-08-21
// raup@itu.dk * 2021-08-27
package exercises02;

import exercises02.ReadWriteMonitorIntrinsic;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLongCounterExperiments {
    LongCounter lc = new LongCounter();
    int counts = 100;
    ReadWriteMonitorIntrinsic lock = new ReadWriteMonitorIntrinsic();

    public TestLongCounterExperiments() {

        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < counts; i++) {
                    if (i % 5 == 0) {
                        lock.writeLock();
                        System.out.println("inc x");
                        Thread.sleep(20);
                        lc.increment();
                        lock.writeUnlock();
                    } else {
                        lock.readLock();
                        Thread.sleep(5);
                        System.out.println(lc.count + " x");
                        lock.readUnlock();
                    }
                }
            } catch (Exception e) {
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                for (int i = 0; i < counts; i++) {
                    if (i % 5 == 0) {
                        lock.writeLock();
                        System.out.println("inc y");
                        Thread.sleep(20);
                        lc.increment();
                        lock.writeUnlock();
                    } else {
                        lock.readLock();
                        Thread.sleep(5);
                        System.out.println(lc.count + " y");
                        lock.readUnlock();
                    }
                }
            } catch (InterruptedException e) {
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException exn) {
            System.out.println("Some thread was interrupted");
        }
        System.out.println("Count is " + lc.get() + " and should be " + 2 * counts);
    }

    public static void main(String[] args) {
        new TestLongCounterExperiments();
    }

    class LongCounter {
        public long count = 0;

        public void increment() {
            // lock.lock();
            count = count + 1;
            // lock.unlock();
        }

        public void decrement() {
            count--;
        }

        public long get() {
            return count;
        }
    }
}

class ReadWriteMonitor {
    private int readers = 0;
    private boolean writer = false;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void readLock() {
        lock.lock();
        try {
            while (writer) {
                condition.await();
            }
            readers++;
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
    }

    public void readUnlock() {
        lock.lock();
        try {
            readers--;
            if (readers == 0)
                condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void writeLock() {
        lock.lock();
        try {
            while (readers > 0 || writer) {
                condition.await();
            }
            writer = true;
        } catch (InterruptedException e) {

        } finally {
            lock.unlock();
        }
    }

    public void writeUnlock() {
        lock.lock();
        try {
            writer = false;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

class ReadWriteMonitorIntrinsic {
    private int readers = 0;
    private boolean writer = false;

    public synchronized void readLock() throws InterruptedException {
        while (writer) {
            this.wait();
        }
        readers++;
    }

    public synchronized void readUnlock() {
        readers--;
        if (readers == 0)
            this.notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        while (readers > 0 || writer) {
            this.wait();
        }
        writer = true;
    }

    public synchronized void writeUnlock() {
        writer = false;
        this.notifyAll();
    }
}