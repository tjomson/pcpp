// package exercises10;

// // JUnit testing imports
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.RepeatedTest;
// import org.junit.jupiter.api.Disabled;

// import org.junit.jupiter.params.ParameterizedTest;
// import org.junit.jupiter.params.provider.ValueSource;
// import org.junit.jupiter.params.provider.MethodSource;
// import org.junit.jupiter.params.provider.Arguments;

// import static org.junit.jupiter.api.Assertions.*;

// // Data structures imports
// import java.util.stream.IntStream;
// import java.util.stream.Stream;
// import java.util.stream.Collectors;
// import java.util.List;
// import java.util.ArrayList;

// // Concurrency imports
// import java.util.concurrent.CyclicBarrier;
// import java.util.concurrent.BrokenBarrierException;
// import java.util.concurrent.Executors;
// import java.util.concurrent.ExecutorService;

// public class TestHistograms {
//     // The imports above are just for convenience, feel free add or remove imports

//     // TODO: 10.1.2

//     public static Histogram test(Histogram histogram, int thread_count) {
//         final int upTo = 5000000;

//         final int workPrThread = upTo / thread_count;
//         final Thread[] threads = new Thread[thread_count];

//         for (int i = 0; i < thread_count; i++) {
//             final int index = i;
//             final int to = ((i * workPrThread) + workPrThread > upTo) ? upTo - (i * workPrThread) : workPrThread;
//             threads[i] = new Thread(() -> {
//                 for (int x = 0; x < to; x++) {
//                     histogram.increment(countFactors((index * workPrThread) + x));
//                 }
//             });
//         }

//         for (Thread thread : threads) {
//             thread.start();
//         }
//         for (Thread thread : threads) {
//             try {
//                 thread.join();
//             } catch (InterruptedException e) {
//             }
//         }

//         return histogram;
//     }

//     // Returns the number of prime factors of `p`
//     public static int countFactors(int p) {
//         if (p < 2)
//             return 0;
//         int factorCount = 1, k = 2;
//         while (p >= k * k) {
//             if (p % k == 0) {
//                 factorCount++;
//                 p = p / k;
//             } else
//                 k = k + 1;
//         }
//         return factorCount;
//     }

//     @Test
//     public void TestCasHistogram() {

//         var histo1 = new Histogram1(5000000);
//         test(histo1, 1);

//         for (int j = 0; j <= 4; j++) {
//             var numThreads = (int) Math.pow(2, j);
//             var histogram = new CasHistogram(5000000);
//             test(histogram, numThreads);

//             for (int i = 0; i < numThreads; i++) {
//                 assertEquals(histo1.getCount(i), histogram.getCount(i));
//             }
//         }
//     }

// }
