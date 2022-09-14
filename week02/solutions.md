# Solutions for week 2

## 2.1.1

See ReadWriteMonitorIntrinsic.java

## 2.1.2

It is fair to writers in the sense, that readers can not starve writers, because when a writer wants to write, no reader is allowed to acquire the lock. That said, the order of the writers is not fair, so a writer might be starved by other writers.

## 2.1.3

Maybe weak, don't quite understand

## 2.2.1

Due to the lack of visibility, there is no guarantee that the first thread will find out that that the other thread changed the variable. This is because the variable is stored in the threads cache, without synchronzing the variable with main memory.

# 2.2.2

See TestMutableInteger for implementation

Adding the intrinsic locks with `synchronized` causes the variable stored in the threads cache to be synced with main memory.
