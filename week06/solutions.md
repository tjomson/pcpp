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
