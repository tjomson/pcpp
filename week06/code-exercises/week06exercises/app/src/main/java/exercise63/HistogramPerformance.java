package exercise63;

import benchmarking.Benchmark;

public class HistogramPerformance {
    
    public static void main(String[] args) {
    
        final var threads = 8;

        // final var histogram2 = new Histogram2(25);
        // Benchmark.Mark7("histogram2_" + threads, (i) -> HistogramPrimesThreads.test(histogram2, threads).getTotal());

        for(int ls = 1; ls <= 32; ls *= 2) {
                final var histogram3 = new Histogram3(25, ls);
                Benchmark.Mark7("histogram3_" + threads + "_" + ls, (i) -> HistogramPrimesThreads.test(histogram3, threads).getTotal());
        }

    }
}
