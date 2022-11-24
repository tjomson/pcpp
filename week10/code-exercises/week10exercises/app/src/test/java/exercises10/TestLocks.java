package exercises10;

// JUnit testing imports
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import static org.junit.jupiter.api.Assertions.*;

// Data structures imports
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

// Concurrency imports
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.concurrent.atomic.AtomicBoolean;

public class TestLocks {
    // The imports above are just for convenience, feel free add or remove imports
    
    // TODO: 10.2.5
    @Test
    public void TestCasLockSeq() {

        ReadWriteCASLock lock = new ReadWriteCASLock();

        // fst
        try {
            assertTrue(lock.writerTryLock());
            assertFalse(lock.readerTryLock());
            assertFalse(lock.writerTryLock());

            lock.writerUnlock();
        }
        catch (Exception e) {
            assertTrue(false);
        }

        // snd
        try {
            assertTrue(lock.readerTryLock());
            assertFalse(lock.writerTryLock());

            lock.readerUnlock();
        }
        catch (Exception e) {
            assertTrue(false);
        }

        // trd
        assertThrows(Exception.class, () -> lock.writerUnlock());
        assertThrows(Exception.class, () -> lock.readerUnlock());
    }

    // TODO: 10.2.6   
    private AtomicInteger active_locks;
    @Test
    public void TestCasLockPar() {
        try {
            ReadWriteCASLock lock = new ReadWriteCASLock();
            active_locks = new AtomicInteger(0);
            
            int task_count = 8;
            Collection<Callable<Boolean>> tasks = new ArrayList<>(task_count);
            for(int i = 0; i < task_count; i++) tasks.add(createWriter(lock));
            
            ExecutorService exec = Executors.newFixedThreadPool(task_count);
            exec.invokeAll(tasks);
            exec.shutdown();
            while(!exec.isTerminated()) {
                if (active_locks.get() > 1) throw new Exception();
            }
        }
        catch (Exception e) {
            assertTrue(false);
            return;
        }
        assertTrue(true);
    }

    private Callable<Boolean> createWriter(ReadWriteCASLock lock) {
        return () -> {
            try {
                while(!lock.writerTryLock()) {
                    active_locks.incrementAndGet();                    
                }
                active_locks.decrementAndGet();
                lock.writerUnlock();
                return true;
            }
            catch (Exception e) { return false; }
        };
    }
}



