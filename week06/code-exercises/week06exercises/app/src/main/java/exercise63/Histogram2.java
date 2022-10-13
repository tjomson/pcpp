package exercise63;

class Histogram2 implements Histogram {
    private final int[] counts;
    private int total=0;

    public Histogram2(int span) {
        synchronized (this) {
            this.counts = new int[span];
        }
    }

    public synchronized void increment(int bin) {
        counts[bin] = counts[bin] + 1;
        total++;
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
    
    public synchronized int getTotal(){
      return total;
    }
}
