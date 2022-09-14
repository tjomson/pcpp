// package exercises02;

// import java.util.concurrent.locks.Condition;
// import java.util.concurrent.locks.Lock;
// import java.util.concurrent.locks.ReentrantLock;

// public class ReadWriteMonitor {
// private int readers = 0;
// private boolean writer = false;
// private Lock lock = new ReentrantLock();
// private Condition condition = lock.newCondition();

// public void readLock() {
// lock.lock();
// try {
// while (writer) {
// condition.await();
// }
// readers++;
// } catch (InterruptedException e) {

// } finally {
// lock.unlock();
// }
// }

// public void readLock2() {
// synchronized (this) {
// while (writer) {
// try {
// this.wait();
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// }
// readers++;
// }
// }

// public void readUnlock() {
// lock.lock();
// try {
// readers--;
// if (readers == 0)
// condition.signalAll();
// } finally {
// lock.unlock();
// }
// }

// public void readUnlock2() {
// synchronized (this) {
// readers--;
// if (readers == 0)
// this.notifyAll();
// }
// }

// public void writeLock() {
// lock.lock();
// try {
// while (readers > 0 || writer) {
// condition.await();
// }
// writer = true;
// } catch (InterruptedException e) {

// } finally {
// lock.unlock();
// }
// }

// public void writeLock2() {
// synchronized (this) {
// while (readers > 0 || writer) {
// try {
// this.wait();
// } catch (InterruptedException e) {
// e.printStackTrace();
// }
// }
// writer = true;
// }
// }

// public void writeUnlock() {
// lock.lock();
// try {
// writer = false;
// condition.signalAll();
// } finally {
// lock.unlock();
// }
// }

// public void writeUnlock2() {
// synchronized (this) {
// writer = false;
// this.notifyAll();
// }

// }
// }