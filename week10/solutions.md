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
