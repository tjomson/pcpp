# Week 10

## 10.1

### 1

See CasHistogram.java

Why they are thread-safe:
getSpan() will not change after the construction of the Histogram-object.
getCount() just gets an atomic value.
increment() and getAndClear() keep trying to set the value until compareAndSet() returns true, indicating success. Thus if a race condition occurs, it will keep trying to set the value, until it succeeds.

### 2

See TestHistograms.java

### 3

See TestCASLockHistogram.java main() method

Results when span is 1.000.000:

```
Testing histogram cas, 1 threads       853240430,9 ns 7686438,74          2
Testing histogram cas, 2 threads       542230997,6 ns 10042137,59         2
Testing histogram cas, 4 threads       306542487,4 ns 8229201,90          2
Testing histogram cas, 8 threads       179566979,4 ns 8607048,59          2
Testing histogram cas, 16 threads      138664907,4 ns 11244760,18         2
Testing histogram locks, 1 threads     866044139,7 ns 8026594,06          2
Testing histogram locks, 2 threads     588620253,2 ns 7807706,19          2
Testing histogram locks, 4 threads     394879937,9 ns 4882654,12          2
Testing histogram locks, 8 threads     414695973,1 ns 21604576,65         2
Testing histogram locks, 16 threads    385323096,9 ns 22942049,94         2
```

The CAS-solution is faster. This makes sense, as Histogram2 does not use lock striping, so all threads are have to fight over the same lock. 
This is not the case for CAS, as each bin is its own atomic int.