package exercises03;

import java.util.concurrent.locks.ReentrantLock;

public class Person {
    private static long _id = -1;
    private static ReentrantLock class_lock = new ReentrantLock();

    public final long id;
    private String name;
    private int zip;
    private String address;
    private ReentrantLock instance_lock = new ReentrantLock();

    public Person() {
        class_lock.lock();
        instance_lock.lock();
        id = ++_id;
        instance_lock.unlock();
        class_lock.unlock();
    }

    public Person(long i) {
        class_lock.lock();
        instance_lock.lock();
        if (_id == -1) {
            id = i;
            _id = i;
        }
        else {
            id = ++_id;
        }
        instance_lock.unlock();
        class_lock.unlock();
    }

    public void changeZipAddr(int n_zip, String n_addr) {
        instance_lock.lock();
        zip = n_zip;
        address = n_addr;
        instance_lock.unlock();
    }

    public int getZip() { return zip; }
    public String getAddr() { return address+""; }
    public String getName() { return name+""; }
}
