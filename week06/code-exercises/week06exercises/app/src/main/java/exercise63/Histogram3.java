package exercise63;

import java.util.concurrent.locks.ReentrantLock;

class Histogram3 implements Histogram {
    private final int[] counts;
    private final ReentrantLock[] locks;
    private int total=0;
    private final ReentrantLock total_lock;

    public Histogram3(int span, int locks) {
        synchronized (this) {
            this.counts = new int[span];
            this.total_lock = new ReentrantLock();
            this.locks = new ReentrantLock[locks];

            for(int i = 0; i < locks; i++) 
                this.locks[i] = new ReentrantLock();
        }
    }

    public void increment(int bin) {
        locks[bin % locks.length].lock();
        counts[bin] = counts[bin] + 1;
        locks[bin % locks.length].unlock();
        total_lock.lock();
        total++;
        total_lock.unlock();
    }

    public int getCount(int bin) {
        locks[bin % locks.length].lock();
        var res = counts[bin];
        locks[bin % locks.length].unlock();
        return res;
    }
    
    public float getPercentage(int bin){
        locks[bin % locks.length].lock();
        var res = getCount(bin) / getTotal() * 100;
        locks[bin % locks.length].unlock();
        return res;
    }

    public int getSpan() {
        return counts.length;
    }
    
    public int getTotal(){
        total_lock.lock();
        var res = total;
        total_lock.unlock();
        return res;
    }
}
