# Solutions for week4

## 4.1

### 1

See line 35-46 + 61-87, in ConcurrentSetTest.java

add e {
    1. if set contains e return
    2. increment size
    3. add e to structure
}

the set is empty
t1 wants to add 2
t2 wants to add 2

Interleaving:
t1(1),t2(1),t2(2),t2(3),t1(2),t2(3)

### 2

See line 48-59 + 89-117, in ConcurrentSetTest.java

remove e {
    1. if set does not contain e return
    2. decrement size
    2. remove e from structure
}

the set contains 2
t1 wants to remove 2
t2 wants to remove 2

Interleaving:
t1(1),t2(1),t2(2),t2(3),t1(2),t1(3)