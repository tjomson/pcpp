// For week 10
// raup@itu.dk * 10/10/2021

package exercises10;

import java.util.concurrent.atomic.AtomicReference;

// Very likely you will need some imports here

class ReadWriteCASLock implements SimpleRWTryLockInterface {

    // TODO: Add necessary field(s) for the class
    AtomicReference<Holders> holders = new AtomicReference<Holders>();

    // 10.2.3
    public boolean readerTryLock() {
        var curr = holders.get();
        while (curr == null || curr.getClass() == ReaderList.class) {
            if (holders.compareAndSet(curr,
                    new ReaderList(Thread.currentThread(), (ReaderList) curr))) {
                return true;
            }
            curr = holders.get();
        }
        return false;
    }

    // 10.2.4
    public void readerUnlock() throws Exception {
        var curr = holders.get();

        if (holders == null || curr.getClass() != ReaderList.class
                || !((ReaderList) curr).contains(Thread.currentThread())) {
            throw new Exception("Trying to unlock thread that does not hold lock");
        }

        while (!holders.compareAndSet(curr, ((ReaderList) curr).remove(Thread.currentThread()))) {
            curr = holders.get();
        }
    }

    // 10.2.1
    public boolean writerTryLock() {
        return holders.compareAndSet(null, new Writer(Thread.currentThread()));
    }

    // 10.2.2
    public void writerUnlock() throws Exception {
        var curr = holders.get();
        holders.get().thread.equals(Thread.currentThread());
        if (curr.thread.equals(Thread.currentThread())) {
            holders.compareAndSet(curr, null);
        } else {
            throw new Exception("Trying to unlock thread that does not hold lock");
        }
    }

    // Challenging 7.2.7: You may add new methods

    private static abstract class Holders {
        public final Thread thread;

        public Holders(Thread thread) {
            this.thread = thread;
        }
    }

    private static class ReaderList extends Holders {
        public final ReaderList next;

        // TODO: Constructor
        public ReaderList(Thread thread, ReaderList next) {
            super(thread);
            this.next = next;
        }

        // TODO: contains
        public boolean contains(Thread toFind) {
            var curr = this;
            while (curr != null) {
                if (this.thread == toFind) {
                    return true;
                }
                curr = curr.next;
            }
            return false;
        }

        // TODO: remove

        public ReaderList remove(Thread toRemove) {
            ReaderList newList = null;
            var curr = this;
            while (curr != null) {
                if (this.thread != toRemove) {
                    newList = new ReaderList(this.thread, newList);
                }
                curr = curr.next;
            }
            return newList;
        }
    }

    private static class Writer extends Holders {

        // TODO: Constructor
        public Writer(Thread thread) {
            super(thread);
        }

    }
}
