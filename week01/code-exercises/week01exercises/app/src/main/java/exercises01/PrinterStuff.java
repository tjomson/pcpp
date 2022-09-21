package exercises01;

import java.util.concurrent.locks.ReentrantLock;

public class PrinterStuff {
    public static void main(String[] args) {
        Printer p = new Printer();
        var lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            while (true) {
                lock.lock();
                p.print();
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                lock.lock();
                p.print();
                lock.unlock();
            }
        });

        t1.start();
        t2.start();
    }
}

class Printer {
    public void print() {
        System.out.print("-");
        try {
            Thread.sleep(50);
        } catch (InterruptedException exn) {
            exn.printStackTrace();
        }
        System.out.print("|");
    }
}
