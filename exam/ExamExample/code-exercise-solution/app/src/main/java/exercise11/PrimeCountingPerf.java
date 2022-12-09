package exercise11;
import java.util.*;
import java.util.stream.*;
import java.io.IOException;
import java.lang.NumberFormatException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.function.IntToDoubleFunction;
import java.util.concurrent.atomic.AtomicLong;

class PrimeCountingPerf { 
  public static void main(String[] args) { new PrimeCountingPerf(); }
  static final int range= 100000;

  //Test whether n is a prime number
  private static boolean isPrime(int n) {
    int k= 2;
    while (k * k <= n && n % k != 0)
      k++;
    return n >= 2 && k * k > n;
  }

// Sequential solution
  private static long countSequential(int range) {
    long count = 0;
    final int from = 0, to = range;
    for (int i=from; i<to; i++)
      if (isPrime(i)) count++;
    return count;
  }

// Stream solution
  private static long countStream(int range) {
    long count= IntStream.range(2, range)
      .filter(i -> isPrime(i))
      .count();
    return count;
  }

// Parallel stream solution
  private static long countParallel(int range) {
    long count= IntStream.range(2, range)
      .parallel()
      .filter(i -> isPrime(i))
      .count();
    return count;
  }

  // --- Benchmarking infrastructure ---
  public static double Mark7(String msg, IntToDoubleFunction f) {
    int n = 10, count = 1, totalCount = 0;
    double dummy = 0.0, runningTime = 0.0, st = 0.0, sst = 0.0;
    do { 
      count *= 2;
      st = sst = 0.0;
      for (int j=0; j<n; j++) {
        Timer t = new Timer();
        for (int i=0; i<count; i++) 
          dummy += f.applyAsDouble(i);
        runningTime = t.check();
        double time = runningTime * 1e9 / count; 
        st += time; 
        sst += time * time;
        totalCount += count;
      }
    } while (runningTime < 0.25 && count < Integer.MAX_VALUE/2);
    double mean = st/n, sdev = Math.sqrt((sst - mean*mean*n)/(n-1));
    System.out.printf("%-25s %15.1f ns %10.2f %10d%n", msg, mean, sdev, count);
    return dummy / totalCount;
  }

  public PrimeCountingPerf() {
    System.out.println("countSequential found: "+ countSequential(range) + " primes");
    Mark7("Sequential", i -> countSequential(range));

    System.out.println("countStream found: "+ countStream(range) + " primes");
    Mark7("IntStream", i -> countStream(range));
    
    System.out.println("countParallel found: "+ countParallel(range) + " primes");
    Mark7("Parallel", i -> countParallel(range));
  }
}
