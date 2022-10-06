package lecture06;
//QuickSort using Java Executor to simulate Problem Heap
//Version jst@itu.dk 19/09 - 2021

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.IOException;

import benchmarking.Benchmark;
import benchmarking.Benchmarkable;

public class QuicksortExecutor {
    private static final int maxThreads = 33;
    private static final int pSize= 1_000_000; // size of array to be sorted
    private static final int threshold= 10000; // problems with size smaller not further subdivided
    private static ExecutorService pool;
  
    public static void main(String[] args){ new QuicksortExecutor(); }

    public QuicksortExecutor() {
	for (int n= 1; n < maxThreads; n= 2*n)  
	    runSize(n, 1_000_000, threshold);
    }

    private static void setUpQS(int threadCount, int[] intArray, int threshold, 
				CyclicBarrier done, AtomicInteger count) {
	pool = Executors.newFixedThreadPool(threadCount);
	// pool = Executors.newWorkStealingPool(threadCount);
	count.set(1); // Initial task count
	pool.execute( new QuicksortTask( new Problem(intArray, 0, intArray.length-1), 
					 pool, threshold, 
					 done, count) );
    }

    private static void finishQS(CyclicBarrier done) {
	try { done.await();}
	catch (InterruptedException | BrokenBarrierException e) {
	    System.out.println(e.toString()); 
	}
    }

    private static void runSize(int threadCount, int pSize, int threshold) {
	final int[] intArray= fillIntArray(pSize);
	final AtomicInteger count= new AtomicInteger(0);
	final CyclicBarrier done = new CyclicBarrier(2);

	Benchmark.Mark8Setup("Executor Quicksort", 
			     String.format("%2d", threadCount),
			     new Benchmarkable() { 
				 public void setup() {
				     shuffle(intArray);
				     setUpQS(threadCount, intArray, threshold, done, count);
				 }
				 public double applyAsDouble(int i) {
				     finishQS(done);
				     // testSorted(intArray);
				     return 0.0;
				 }
			     }
			     );
    }


    // Quicksort Utils
    private static void testSorted(int[] a) {
	int c= 0;
	while ( c < (a.length-1) ) 
	    if (a[c] <= a[c+1]) c= c+1;
	    else { System.out.println("Error at "+c); break; }
	//a is ordered
	if (c == a.length-1) System.out.println("Success!");
    }

    public static int[] fillIntArray(int n) {
	int [] arr = new int[n];
	for (int i = 0; i < n; i++)
	    arr[i] = i;
	return arr;
    }

    private static final java.util.Random rnd = new java.util.Random();

    public static void shuffle(int[] arr) {
	for (int i = arr.length-1; i > 0; i--)
	    swap(arr, i, rnd.nextInt(i+1));
    }

    public static void swap(int[] arr, int s, int t) {
	int tmp = arr[s];  arr[s] = arr[t];  arr[t] = tmp;
    }
}

  
