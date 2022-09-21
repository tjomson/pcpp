# Solutions for week1

# E1.1

## 1

19926221

## 2

We get 200 every time. The reason for this is that there is a lower chance for race conditions when it runs fewer passes. It is not guaranteed.

We calculated the chance for a non-skipped number to be 19926221/20000000 = 99.63%
Meaning it is unlikely with low numbers, that it skips.

## 3

Don't think it makes any difference, as the compiled result still creates a temp-variable and then assigns count.
Results seem the same.

## 4

Right before updating the counter, we lock the lock, such that only the current thread has access to the critical section (mutual exclusion). After setting the counter, we unlock the lock again, allowing the other thread access to it, thus avoiding a race condition

## 5

Splitting the incrementation into a line containing `var temp = count` and another line `count = temp+1`, it is not possible to put just one of them in the critical section, without causing race conditions. So current solution is already optimal

## 6

There are no difference in the assembly.

```
public void increment();
    Code:
       0: aload_0
       1: dup
       2: getfield      #7                  // Field count:J
       5: lconst_1
       6: ladd
       7: putfield      #7                  // Field count:J
      10: return
```

Create 3 copies of TestLongCounterExperiments, with different implementations.

Then run with gradle (to build class files) and dissasemble with javap (to compare assembly):

```bash
gradle -p code-exercises/week01exercises/ -PmainClass=exercises01.TestLongCounterExperiments1 run
gradle -p code-exercises/week01exercises/ -PmainClass=exercises01.TestLongCounterExperiments2 run
gradle -p code-exercises/week01exercises/ -PmainClass=exercises01.TestLongCounterExperiments3 run

javap -c code-exercises/week01exercises/app/build/classes/java/main/exercises01/TestLongCounterExperiments1\$LongCounter.class
javap -c code-exercises/week01exercises/app/build/classes/java/main/exercises01/TestLongCounterExperiments2\$LongCounter.class
javap -c code-exercises/week01exercises/app/build/classes/java/main/exercises01/TestLongCounterExperiments3\$LongCounter.class
```

## 7

Expected value will be somewhere between -10_000_000 and 10_000_000

To fix, adding a lock that we lock right before incrementing or decrementing, and then unlocking afterwards. Ensures only one thread can mutate count at a time.

## 9

Min-value is 2

Example with `counts=5` :

```
count : 0       1       2       3       4    1          2       3       4       5    2
t1    :   r1 w1   r2 w2   r3 w3   r4 w4        r5                                 w5  
t2    :   r1                              w1      r2 w2   r3 w3   r4 w4   r5 w5       

where:

count is Shared memory
t1    is Thread 1
t2    is Thread 2
r     is Read
w     is write
```

# E1.2

## 1 

See PrinterStuff.java

## 2

1 = System.out.print("-");
2 = try { Thread.sleep(50); } catch (InterruptedException exn) {}
3 = System.out.print("|");

t1(1) t1(2) t2(1) t2(2) ... 

The two threads might be schedueled, such that while t1 sleeps t2 enters the core and begins executing.

## 3

See the added lock in PrinterStuff.java

Now we lock before printing, and unlock when done printing. This means that a thread will always print "-|" completely before another thread can print, thus avoiding the interleaving that causes the incorrect output

## 4

Because an 'unlock()' must happen before a 'lock()', once one of the threads start executing its loop body (critical section), i.e. makes a call to 'lock()', the other thread must wait until the executing thread calls 'unlock()'.

# E1.3

## 1

See CounterThreads2Covid.java line 39-42

## 2

We ensure that only one thread can access the critical section at a time using the locks. Inside the critical section, we also check whether the max number of people has been reached before incrementing.
