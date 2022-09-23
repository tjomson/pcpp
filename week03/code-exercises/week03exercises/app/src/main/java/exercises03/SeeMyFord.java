package exercises03;

public class SeeMyFord {
    private long permits = Long.MIN_VALUE;
    private boolean hasWaiting = false;

    public SeeMyFord(long init_permits) {
        permits = init_permits;
    }

    public synchronized void acquire() throws InterruptedException {

        if (permits <= 0) {
            hasWaiting = true;
            wait();
        }
        permits--;
        return;
    }

    public synchronized boolean noPermits() {
        return permits == 0;
    }

    public synchronized void release() {
        permits++;
        if (hasWaiting) {
            hasWaiting = false;
            notifyAll();
        }
        return;
    }

    public static void main(String[] args) {
        SeeMyFord smf = new SeeMyFord(2);

        Thread t1 = new Thread(() -> {
            try {
                smf.acquire();
                System.out.println("T1");
            } catch ( InterruptedException e ) {
                System.out.println("T1 fail");
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                smf.acquire();
                System.out.println("T2");
            } catch ( InterruptedException e ) {
                System.out.println("T2 fail");
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                smf.acquire();
                System.out.println("T3");
            } catch ( InterruptedException e ) {
                System.out.println("T3 fail");
            }
        }); 

        t1.start();
        t2.start();
        t3.start();
    }
}
