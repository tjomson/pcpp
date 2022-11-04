package exercise63;

import java.util.concurrent.locks.ReentrantLock;

class Histogram2 implements Histogram {
    private final int[] counts;
    private int total=0;
    private final ReentrantLock total_lock;
    
    public Histogram2(int span) {
        synchronized (this) {
            this.counts = new int[span];
            this.total_lock = new ReentrantLock();
        }
    }

    public synchronized void increment(int bin) {
        synchronized (this) {    
            counts[bin] = counts[bin] + 1;
        }
        total_lock.lock();
        total++;
        total_lock.unlock();
    }

    public synchronized int getCount(int bin) {
        return counts[bin];
    }
    
    public synchronized float getPercentage(int bin){
      return getCount(bin) / getTotal() * 100;
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
