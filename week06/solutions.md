# Week 6 exercises

## 6.1

### 1

We ran it with 3 different numbers of transations, and we see that the time scales linearly

NO_TRANSACTION=5: 251230208,6 ns
NO_TRANSACTION=10: 502338617,0 ns
NO_TRANSACTION=15: 752989044,7 ns

See AccountExperiments.java line 21

### 2

It is important to lock the lower id first and then the higher id, in case that the same two people want to send money to each other at the same time. Then they will both acquire the outer locks, but will wait for each other for the inner lock, causing a deadlock.

Using min/max, the program terminates in 2 seconds. Not doing so, deadlocks the program. As can be seen from this, it still hasn't completed after 3 min 32 sec
```
Transfer 1066 from 5 to 6
Transfer 2595 from 7 to 4
Transfer 753 from 2 to 3
Transfer 3967 from 6 to 3
<=========----> 75% EXECUTING [3m 32s]
```

### 3 + 4

We added a print to doNTransactions, as this is the method that each thread calls. We also converted the lambda function to a class implementing Runnable.

See ThreadsAccountExperimentsMany line 16, 22-44, 94-99

## 6.2

### 1

See TestCountPrimesThreads.out for the data

We see that it speeds up until 8 threads are used, after which it levels off. This was run on a PC with 16 threads, so we expected it to keep getting better up to 16 threads. We also see huge deviation when 8 threads are used. The local and non-local version seem to perform the same.

## 6.3

### 1

See Histogram2.java

'counts' has been made final to make explicit that the array wont be swithed out, during the objects life time. This might not strictly be nessecary, as the field is not changed by any method, and wont escape.

The constructor has been synchronized on the object itself, so that the contructor must happen before anything else.

'increment' has been synchronized, to ensure that both field are actually incremented.

'getPercentage' has been syncronized, to ensure that the 'getCount' and 'getTotal' are from the same state, otherwise they might not match.

'getSpan' has not be synchronized, as the lenght of the array is constant, and thus will not change in any interleaving.

'getTotal' has been syncronized, as 'total' might change.