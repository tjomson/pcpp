package exercises10;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class CasHistogram implements Histogram {
    AtomicInteger[] bins;

    public CasHistogram(int span) {
        this.bins = new AtomicInteger[span];
        for (int i = 0; i < span; i++) {
            bins[i] = new AtomicInteger(0);
        }
    }

    @Override
    public void increment(int bin) {
        AtomicInteger ai;
        int curr;

        do {
            ai = bins[bin];
            curr = ai.get();
        } while (!ai.compareAndSet(curr, curr + 1));
    }

    @Override
    public int getCount(int bin) {
        return bins[bin].get();
    }

    @Override
    public int getSpan() {
        return bins.length;
    }

    @Override
    public int getAndClear(int bin) {
        var ai = bins[bin];
        var curr = ai.get();
        while (!ai.compareAndSet(curr, 0)) {
            curr = ai.get();
        }
        return curr;

    }

}
