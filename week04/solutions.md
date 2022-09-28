# Solutions for week4

## 4.1

### 1

See line 35-46 + 61-87, in ConcurrentSetTest.java

add e {
    1. if set contains e return
    2. add e to structure
    3. increment size
}

the set is empty
t1 is add 2
t2 is add 2

Interleaving:
t1(1),t2(1),t2(2),t2(3),t1(2),t2(3)

### 2

See line 48-59 + 89-117, in ConcurrentSetTest.java

remove e {
    1. if set does not contain e return
    2. remove e from structure
    3. decrement size
}

the set contains 2
t1 is remove 2
t2 is remove 2

Interleaving:
t1(1),t2(1),t2(2),t2(3),t1(2),t1(3)

### 3

See line 42 + 46 + 50, in ConcurrentIntegerSet.

### 4

### 5

Yes it does. It showes the existence of atleast one possible interleaving, in which the collection fails to behave as expected.

### 6

No. Testing cannot be used to prove such properties, there are simply to many possible test cases. If proof is need, it must the created by other means, happens-before relations, via a proof assistant, via SAT, via SMT etc.

### 7

size {
    1. return size
}

the set contains 2
t1 is size
t2 is remove 2

Interleaving:
t2(1),t2(2),t1(1),t2(3)