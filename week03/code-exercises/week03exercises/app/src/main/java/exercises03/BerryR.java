package exercises03;

public class BerryR {
    private SeeMyFord smf;

    public BerryR(int s) {
        smf = new SeeMyFord(s);
    }

    public synchronized void await() throws InterruptedException {
        smf.acquire();
        if (smf.noPermits()) notifyAll();
        else wait();
    }

    public static void main(String[] args) {
        BerryR br = new BerryR(3);
        
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("T1 waiting...");
                br.await();
                System.out.println("T1 says fuck you!");
            } catch (InterruptedException e) {
                System.out.println("T1 failure :(");
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("T2 waiting...");
                br.await();
                System.out.println("T2 says fuck you!");
            } catch (InterruptedException e) {
                System.out.println("T2 failure :(");
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                System.out.println("T3 waiting...");
                br.await();
                System.out.println("T3 says fuck you!");
            } catch (InterruptedException e) {
                System.out.println("T3 failure :(");
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
