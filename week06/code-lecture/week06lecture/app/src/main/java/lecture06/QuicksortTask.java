package lecture06;
//Quicksort implemented with Executors
//Version jst@itu.dk 18/09 - 2021
// raup@itu.dk * 03/10/2022

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.atomic.AtomicInteger;
import java.io.IOException;

import benchmarking.Benchmark;

class QuicksortTask implements Runnable {
    private Problem p;
    private ExecutorService pool;
    private static int threshold;
    private CyclicBarrier done;
    private AtomicInteger count;
    
    public QuicksortTask(Problem p, ExecutorService pool, int threshold, 
			 CyclicBarrier done, AtomicInteger count) {
	this.p = p; 
	this.pool= pool; 
	this.threshold= threshold;
	this.done = done;
	this.count = count;
    }

    @Override
    public void run() { qsort(p, pool, true, done, count); }
  
    // Quicksort where recursive calls are replaced by submitting new task to executor
    public static void qsort(Problem problem, ExecutorService pool, boolean newTask, 
			     CyclicBarrier done, AtomicInteger count) { 
	int[] arr= problem.arr;
	int a= problem.low;
	int b= problem.high;
    
	if (a < b) {
	    int i = a, j = b;
	    int x = arr[(i+j) / 2];                
	    do {  
		while (arr[i] < x) i++;              
		while (arr[j] > x) j--; 
		if (i <= j) {
		    swap(arr, i, j);
		    i++; j--;
		}                                    
	    } while (i <= j);



	    if ((j-a)>= threshold) count.incrementAndGet();
	    if ((b-i)>= threshold) count.incrementAndGet();


	    if ((j-a)>= threshold)
		pool.execute( new QuicksortTask( new Problem(arr, a, j), pool, threshold, 
						 done, count) );
	    else 
		qsort(new Problem(arr, a, j), pool, false, done, count);


	    if ((b-i)>= threshold)
		pool.execute( new QuicksortTask( new Problem(arr, i, b), pool, threshold, 
						 done, count) );
	    else 
		qsort(new Problem(arr, i, b), pool, false, done, count);
	}

	if (newTask) {
	    if (count.decrementAndGet() == 0) {
		try{done.await();}
		catch(InterruptedException | BrokenBarrierException e) {System.out.println(e); }
		pool.shutdown(); 
	    }
	}
    }

    // Quicksort utils
    public static void swap(int[] arr, int s, int t) {
	int tmp = arr[s];  arr[s] = arr[t];  arr[t] = tmp;
    }
}
