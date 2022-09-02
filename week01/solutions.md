# Solutions for week1

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