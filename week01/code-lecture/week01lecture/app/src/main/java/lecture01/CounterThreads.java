// For week 1
// raup@itu.dk * 2021-08-27
package lecture01;

public class CounterThreads {

    // Shared variables
    long counter = 0;
    final long PEOPLE  = 10_000;

    public CounterThreads() {
	try {
	    // Create an instance of the thread
	    Turnstile turnstile = new Turnstile();

	    // Start the execution of the thread
	    turnstile.start();

	    // Wait until the thread finishes its execution
	    turnstile.join();

	    // Print the value of counter
	    System.out.println(counter+" people entered");	    
	}
	catch (InterruptedException e) { // join() may throw InterruptedExceptions
	    System.out.println("Error " + e.getMessage());
	    e.printStackTrace();
	}
    }

    // We define the code in the class constructor, CounterThreads(),
    // to avoid problems with using non-static variables in the body
    // of main().
    // In future lectures we will talk about the subtleties of static
    // variables in Java concurrency.
    public static void main(String[] args) {
	new CounterThreads();
    }


    // Definition of the thread computation
    public class Turnstile extends Thread {
	public void run() {	    
	    for (int i = 0; i < PEOPLE; i++) {
		counter++;
	    }
	}
    }
}
