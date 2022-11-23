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
Testing histogram cas, 1 threads        857915590,0 ns 51830611,53         2
Testing histogram cas, 2 threads        548234276,0 ns 6494529,42          2
Testing histogram cas, 4 threads        305651493,4 ns 11044877,77         2
Testing histogram cas, 8 threads        179843784,1 ns 18481733,80         2
Testing histogram cas, 16 threads       141132102,2 ns 9254626,47          2
Testing histogram locks, 1 threads      846449209,5 ns 13350060,22         2
Testing histogram locks, 2 threads      585966394,2 ns 6279124,90          2
Testing histogram locks, 4 threads      390255437,4 ns 7485267,73          2
Testing histogram locks, 8 threads      420165326,4 ns 34584943,15         2
Testing histogram locks, 16 threads     422686380,7 ns 24813023,06         2
```

The CAS-solution is faster. This makes sense, as Histogram2 does not use lock striping, so all threads are have to fight over the same lock. 
This is not the case for CAS, as each bin is its own atomic int.