package exercises03;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class BoundedBufferStuff {
    public static void main(String[] args) {

        BoundedBufferInterface<String> buf = new BoundedBuffer<String>(5);
        Thread t1 = new Thread(() -> {
            try {
                buf.insert("elem1");
                buf.insert("elem2");
                buf.insert("elem3");
                buf.insert("elem4");
                buf.insert("elem5");
                Thread.sleep(5000);
                buf.insert("elem6");
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        Thread t2 = new Thread(() -> {
            try {

                var a = buf.take();
                System.out.println(a);
                var b = buf.take();
                System.out.println(b);
                var c = buf.take();
                System.out.println(c);
                var d = buf.take();
                System.out.println(d);
                var e = buf.take();
                System.out.println(e);
                var f = buf.take();
                System.out.println(f + "ahhh");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}

class BoundedBuffer<T> implements BoundedBufferInterface<T> {
    private Semaphore semInserter;
    private Semaphore semTaker;
    private LinkedList<T> queue;

    public BoundedBuffer(int permits) {
        semInserter = new Semaphore(permits, true);
        semTaker = new Semaphore(0, true);
        queue = new LinkedList<>();
    }

    @Override
    public void insert(T elem) throws Exception {
        semInserter.acquire();
        queue.add(elem);
        semTaker.release();
    }

    @Override
    public T take() throws Exception {
        semTaker.acquire();
        var item = queue.pop();
        semInserter.release();
        return item;
    }
}
