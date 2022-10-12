package exercises05;
// jst@itu.dk * 2022-09-06

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntToDoubleFunction;
import java.util.concurrent.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class TestTimeSearch {
  public static void main(String[] args) { new TestTimeSearch(); }

  public TestTimeSearch() {
    final String filename = "src/main/resources/long-text-file.txt";
    final String target= "ipsum";

    final LongCounter lc= new LongCounter();
    String[] lineArray = readWords(filename);

    System.out.println("Array Size: "+ lineArray.length);
    System.out.println("# Occurences of "+target+ " :"+search(target, lineArray, 0, lineArray.length, lc));
    Mark7("par_search32", (x) -> {return (double)countParallelN(target, lineArray, 32, lc);});

    Mark7("seq_search", (i) -> {return (double)search(target, lineArray, 0, lineArray.length, lc);});
    for(int i = 1; i <= 32; i++) {
      final int threadCount = i;
      Mark7("par_search"+i, (x) -> {return (double)countParallelN(target, lineArray, threadCount, lc);});
    }
  }

  private static long countParallelN(String target, String[] lineArray, int N, LongCounter lc) {
    int workPrThread = lineArray.length / N;
    Thread[] threads = new Thread[N];
    for(int i = 0; i < N; i++) {
      final int from = i*workPrThread;
      int to = (from + workPrThread > lineArray.length)? (i*workPrThread)+(lineArray.length-1) : (i*workPrThread)+workPrThread ;
      threads[i] = new Thread(() -> {
        search(target, lineArray, from, to, lc);
      });
    }
    for (int t=0; t<N; t++) 
      threads[t].start();
    try {
      for (int t=0; t<N; t++) 
        threads[t].join();
    } catch (InterruptedException exn) { }
    return lc.get();
  }

  static long search(String x, String[] lineArray, int from, int to, LongCounter lc){
    //Search each line of file
    for (int i=from; i<to; i++ ) lc.add(linearSearch(x, lineArray[i]));
    //System.out.println("Found: "+lc.get());
    return lc.get();
  }

  static long linearSearch(String x, String line) {
    //Search for occurences of c in line
    String[] arr= line.split(" ");
    long count= 0;
    for (int i=0; i<arr.length; i++ ) if ( (arr[i].equals(x)) ) count++;                   
    return count;
  }

  public static String[] readWords(String filename) {
    try {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        return reader.lines().toArray(String[]::new);   //will be explained in Week07;
    } catch (IOException exn) { return null;}
  }

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
        double time = runningTime * 1e9 / count; // nanoseconds
        st += time; 
        sst += time * time;
        totalCount += count;
      }
    } while (runningTime < 0.25 && count < Integer.MAX_VALUE/2);
    double mean = st/n, sdev = Math.sqrt((sst - mean*mean*n)/(n-1));
    System.out.printf("%-25s %15.1f ns %10.2f %10d%n", msg, mean, sdev, count);
    return dummy / totalCount;
  }

  public static void SystemInfo() {
    System.out.printf("# OS:   %s; %s; %s%n", 
                      System.getProperty("os.name"), 
                      System.getProperty("os.version"), 
                      System.getProperty("os.arch"));
    System.out.printf("# JVM:  %s; %s%n", 
                      System.getProperty("java.vendor"), 
                      System.getProperty("java.version"));
    // The processor identifier works only on MS Windows:
    System.out.printf("# CPU:  %s; %d \"cores\"%n", 
                      System.getenv("PROCESSOR_IDENTIFIER"),
                      Runtime.getRuntime().availableProcessors());
    java.util.Date now = new java.util.Date();
    System.out.printf("# Date: %s%n", 
    new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(now));
  }
}
