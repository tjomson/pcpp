package exercises10;

class Histogram1 implements Histogram {
    private int[] counts;
    private int total = 0;

    public Histogram1(int span) {
        this.counts = new int[span];
    }

    public void increment(int bin) {
        counts[bin] = counts[bin] + 1;
        total++;
    }

    public int getCount(int bin) {
        return counts[bin];
    }

    public float getPercentage(int bin) {
        return getCount(bin) / getTotal() * 100;
    }

    public int getSpan() {
        return counts.length;
    }

    public int getTotal() {
        return total;
    }

    public int getAndClear(int bin) {
        var val = counts[bin];
        counts[bin] = 0;
        return val;
    }
}
