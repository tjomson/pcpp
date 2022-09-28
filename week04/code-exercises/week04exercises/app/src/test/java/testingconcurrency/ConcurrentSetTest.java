package testingconcurrency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
// TODO: Very likely you need to expand the list of imports
import org.junit.jupiter.api.Test;

public class ConcurrentSetTest {

    // Variable with set under test
    private ConcurrentIntegerSet set;

    // TODO: Very likely you should add more variables here
        

    // Uncomment the appropriate line below to choose the class to
    // test
    // Remember that @BeforeEach is executed before each test
    @BeforeEach
    public void initialize() {
	// init set
	set = new ConcurrentIntegerSetBuggy();
	// set = new ConcurrentIntegerSetSync();	
	// set = new ConcurrentIntegerSetLibrary();
    }

    // TODO: Define your tests below

    private Thread set_adder(int a, CyclicBarrier start, Semaphore finish) {
        return new Thread(() -> {
            try {
                start.await();
                set.add(a);
                finish.release();
            } catch (Exception e) {
                System.out.println("A set_adder failed");
                return;
            }
        });
    }

    private Thread set_remover(int a, CyclicBarrier start, Semaphore finish) {
        return new Thread(() -> {
            try {
                start.await();
                set.remove(a);
                finish.release();
            } catch (Exception e) {
                System.out.println("A set_remover failed");
                return;
            }
        });
    }

    @Test
    @RepeatedTest(5000)
    public void add_ConcurrentlyCorrect() {
        // Parameters
        int thread_count = 32;
        int unique_elements = 10;

        // Setup
        CyclicBarrier start_barrier = new CyclicBarrier(thread_count);
        Semaphore finish = new Semaphore((-thread_count)+1);

        // Start execution
        for (int i = 0; i < thread_count; i++) {
            set_adder(i % unique_elements, start_barrier, finish).start();
        }

        // Wait for the threads to finish
        try {
            finish.acquire();
        } catch (Exception e) {
            System.out.println("Test failure");
            assertFalse(true);
            return;
        }
            
        assertEquals(unique_elements, set.size());
    }

    @Test
    @RepeatedTest(5000)
    public void remove_ConcurrentlyCorrect() {
        // Parameters
        int remove_times = 2;
        int unique_elements = 10;

        // Setup
        CyclicBarrier start_barrier = new CyclicBarrier(remove_times*unique_elements);
        Semaphore finish = new Semaphore((-(remove_times*unique_elements))+1);
        for (int i = 0; i < unique_elements; i++) set.add(i);

        // Start execution
        for (int i = 0; i < (remove_times*unique_elements); i++) {
            set_remover(i % unique_elements, start_barrier, finish).start();
        }

        // Wait for the threads to finish
        try {
            finish.acquire();
        } catch (Exception e) {
            System.out.println("Test failure");
            assertFalse(true);
            return;
        }

        assertEquals(0, set.size());
    }
}
