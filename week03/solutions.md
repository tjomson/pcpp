# Solutions for week3

## 3.1

### 1
See class BoundedBuffer in BoundedBufferStuff.java

### 2

To explain this, we need to look at the possible method calls, and whether they can cause race conditions.

To handle mutual exclusion to the queue, we have added a semaphore that behaves like a lock, because it only has 1 permit.
We have 2 methods, take and insert. A thread can only insert or take, if doing so does not change the size of the queue to less than 0 or more than the allowed permits. Also, the lock-semaphore makes sure that only one thread can access the critical section, thus avoiding race conditions

### 3

No. If we use barriers, the barrier will wait for a specific number of takers before letting them all in. This does not take into account the number of elements in the queue, so there might not be enough elements for all of the takers


## 3.2

### 1

See class Person in Person.java

### 2

It would not be thread-safe, if multiple constructors could be called at the same time, such that a race condition would exist on the static field _id. This is prevented via the class level intrinsic lock in both constructors.

It would not be thread-safe, if a filed access to id could occure, before the field is set in the constructor. This is prevented via the instance level intrinsic lock in both constructors.

It would not be thread-safe if a reference to on of the String fields escape the object. This cannot happen as a new String object is created via String concatanation.

It would not be thread-safe if multiple calls modify the zip and address fields of an object. This will not happen due to the instance level intrinsic lock, on the method changeZipAddr().

Additionally, only final fields of simple types are public, and thus cannot be modified.

### 3

See main method in Person.java

### 4

No. Testing cannot be used to prove such properties, there are simply to many test cases. If proof is need, it must the created by other means, happens-before relations, via a proof assistant, via SAT, via SMT etc.