# Week 7

## 7.2

### 1

Results:
```
Sequential                     11686202,6 ns  124529,33         32
IntStream                             0,7 ns       0,00  536870912
Parallel                              2,6 ns       0,01  134217728
ParallelStream                        2,6 ns       0,01  134217728
```
### 2

Results:

```
Sequential                     19659781,2 ns   30378,30         16
IntStream                      11687330,9 ns    6568,15         32
Parallel                              0,7 ns       0,00  536870912
ParallelStream                        2,6 ns       0,00  134217728
```

### 3

Added this:
```
.map(x -> {
          System.out.println(x);
          return x;
        })
```

### 4 & 5

I only gave Parallel 8 threads, so that might impact its result. The streams don't use a fixed number.

Sequential                     19812749,3 ns   83361,99         16
IntStream                      11729666,2 ns   42947,21         32
Parallel                        3364843,7 ns  137196,17        128
ParallelStream                  1678686,2 ns   14901,70        256

## 7.3

TestWordStream.java

### 1

See readWords method

### 2

See print100 method

### 3

See moreThan22Letters method

### 4

See someWordWithAtLeast22Letters method

### 5

See isPalindrome and printPalindromes methods

### 6

We can't use Mark7, as that requires a int=>double function.
Timing with Timer() shows pretty much no difference

### 7

See method readWordsOnline
There are 235883 words

### 8

Result: 1 24 9.569126612007494

See printStats method
