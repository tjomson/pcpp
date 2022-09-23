package exercises01;

import java.util.concurrent.locks.ReentrantLock;

public class PrinterStuff {
    public static void main(String[] args) {
        Printer p = new Printer();

        Thread t1 = new Thread(() -> {
            while (true) {
                p.print();
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                p.print();
            }
        });

        t1.start();
        t2.start();
    }
}

class Printer {
    ReentrantLock lock = new ReentrantLock();

    public void print() {
        System.out.print("-");
        try {
            lock.lock();
            Thread.sleep(50);
            lock.unlock();
        } catch (InterruptedException exn) {
            exn.printStackTrace();
        }
        System.out.print("|");
    }
}
