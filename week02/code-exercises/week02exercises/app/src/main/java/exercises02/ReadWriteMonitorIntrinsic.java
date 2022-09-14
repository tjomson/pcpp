package exercises02;

public class ReadWriteMonitorIntrinsic {
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