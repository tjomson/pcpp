# Solutions for week3

## 3.1

### 1
See class BoundedBuffer in BoundedBufferStuff.java

### 2

To explain this, we need to look at the possible method calls, and whether they can cause race conditions.

To handle mutual exclusion to the queue, we have added a semaphore that behaves like a lock, because it only has 1 permit.
We have 2 methods, take and insert. A thread can only insert or take, if doing so does not change the size of the queue to less than 0 or more than the allowed permits. Also, the lock-semaphore makes sure that only one thread can access the critical section, thus avoiding race conditions

