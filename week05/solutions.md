# Exercise 05

## 1
### 1.1

Our results are very comparable to the results in the notes.

```
[Mark 1]
 0.004 s     0.2ns 
[Mark 2]
  24.8 ns
[Mark 3]
  26.5 ns
  25.4 ns
  24.4 ns
  25.6 ns
  24.7 ns
  25.7 ns
  24.1 ns
  24.4 ns
  24.1 ns
  25.7 ns
[Mark 4]
  25.5 ns +/-  0.559
[Mark 5]
 178.6 ns +/-   242.72          2
  69.0 ns +/-    25.85          4
  66.2 ns +/-    15.27          8
  58.6 ns +/-     8.13         16
  68.9 ns +/-    33.19         32
  47.6 ns +/-     8.04         64
  29.5 ns +/-     2.11        128
  44.0 ns +/-     1.87        256
  44.3 ns +/-     1.55        512
  34.2 ns +/-     7.54       1024
  33.4 ns +/-     7.19       2048
  36.9 ns +/-     8.74       4096
  26.2 ns +/-     2.57       8192
  26.4 ns +/-     1.48      16384
  25.4 ns +/-     1.33      32768
  25.5 ns +/-     0.82      65536
  26.5 ns +/-     1.18     131072
  26.8 ns +/-     0.54     262144
  26.9 ns +/-     0.49     524288
  25.4 ns +/-     1.01    1048576
  25.0 ns +/-     1.23    2097152
  24.9 ns +/-     0.95    4194304
  24.7 ns +/-     0.64    8388608
  25.2 ns +/-     1.09   16777216
[Mark 6]
multiply                            476.4 ns     957.53          2
multiply                            160.2 ns      45.44          4
multiply                            213.4 ns      62.85          8
multiply                            196.7 ns      31.94         16
multiply                            185.5 ns      17.88         32
multiply                            348.0 ns     150.16         64
multiply                             58.1 ns       8.95        128
multiply                             55.7 ns       2.15        256
multiply                             56.5 ns       4.92        512
multiply                             48.8 ns       0.53       1024
multiply                             49.6 ns       1.41       2048
multiply                             49.4 ns       3.31       4096
multiply                             26.3 ns       1.93       8192
multiply                             26.3 ns       1.35      16384
multiply                             25.4 ns       1.12      32768
multiply                             24.7 ns       1.52      65536
multiply                             25.1 ns       1.12     131072
multiply                             25.3 ns       1.28     262144
multiply                             25.0 ns       1.28     524288
multiply                             24.7 ns       0.68    1048576
multiply                             25.3 ns       1.28    2097152
multiply                             26.4 ns       1.29    4194304
multiply                             24.2 ns       0.54    8388608
multiply                             24.0 ns       0.22   16777216
```

### 1.2

```
# OS:   Windows 10; 10.0; amd64
# JVM:  Oracle Corporation; 18.0.2.1
# CPU:  AMD64 Family 23 Model 96 Stepping 1, AuthenticAMD; 16 "cores"
# Date: 2022-10-10T15:33:48+0200
pow                                  15,9 ns       0,47   16777216
exp                                   6,6 ns       0,19   67108864
log                                   9,0 ns       0,11   33554432
sin                                  10,9 ns       0,51   33554432
cos                                  10,5 ns       0,19   33554432
tan                                  13,6 ns       0,13   33554432
asin                                125,2 ns       0,38    2097152
acos                                115,3 ns       0,58    4194304
atan                                 34,5 ns       0,44    8388608
```
```
# OS:   Linux; 5.15.0-48-generic; amd64
# JVM:  Private Build; 18.0.2-ea
# CPU:  null; 8 "cores" 
# Date: 2022-10-10T15:27:12+0200
pow                                  18.6 ns       1.10   16777216
exp                                  11.1 ns       0.65   33554432
log                                  13.3 ns       2.57   33554432
sin                                  13.6 ns       0.20   33554432
cos                                  14.4 ns       1.58   33554432
tan                                  21.3 ns       1.67   16777216
asin                                 74.8 ns       3.70    4194304
acos                                 70.9 ns       0.70    4194304
atan                                 20.4 ns       0.58   16777216
```
The cpu on the linux machine is a Intel(R) Core(TM) i7-7700HQ CPU @ 2.80GHz / 3.8GHz

The cpu on the windows machine is a AMD Ryzen 7 Pro 4750U @ 1.70GHz / 4.1GHz

The windows machine is slower on arcsin, arccos and arctan and faster at all
other operations.

Its possible the windows machine is faster, since the boost clockspeed of 4.1GHz
is faster, or more maybe its caches is larger or faster, which all could have a
big impact.

## 2
### 2.1

The standard deviation and mean decreases with the count increasing, as expected

### 2.2
```
# OS:   Linux; 5.15.0-48-generic; amd64
# JVM:  Private Build; 18.0.2-ea
# CPU:  null; 8 "cores"
# Date: 2022-10-10T15:59:02+0200
Mark 6 measurements
hashCode()                            2.5 ns       0.02  134217728
Point creation                       36.0 ns       0.78    8388608
Thread's work                      5396.4 ns     136.42      65536
Thread create                       619.0 ns      10.19     524288
Thread create start               47576.1 ns     995.09       8192
Thread create start join          76897.2 ns    1894.72       4096
ai value = 1556420000
Uncontended lock                     17.6 ns       0.14   16777216
```

Deviation results for "Thread create start join" is significantly higher than
"Thread create start", probably due to randomness in scheduling.

## 3
### 3.1

```
# OS:   Windows 10; 10.0; amd64
# JVM:  Oracle Corporation; 18.0.2.1
# CPU:  AMD64 Family 23 Model 96 Stepping 1, AuthenticAMD; 16 "cores"
# Date: 2022-10-12T11:41:04+0200
countSequential                13429051,6 ns 1063690,02         32
countParallelN       1         15338491,2 ns  644817,00         32
countParallelN       2          9998961,9 ns  447322,27         32
countParallelN       3          7604409,5 ns  266067,31         64
countParallelN       4          6256883,3 ns  555464,74         64
countParallelN       5          5499208,3 ns  392496,64         64
countParallelN       6          5081678,0 ns  829877,85         64
countParallelN       7          4300405,0 ns  517830,65         64
countParallelN       8          4747984,8 ns  260721,26         64
countParallelN       9          4617485,3 ns  146854,63         64
countParallelN      10          4481477,0 ns  159018,26         64
countParallelN      11          4639519,8 ns   88953,29         64
countParallelN      12          4550510,3 ns   38977,14         64
countParallelN      13          4205156,9 ns   61898,30         64
countParallelN      14          4223232,2 ns   42466,85         64
countParallelN      15          4235961,6 ns   58069,07         64
countParallelN      16          4323497,3 ns  118885,45         64
countParallelN      17          4377938,1 ns  120654,86         64
countParallelN      18          4415060,6 ns  166248,17         64
countParallelN      19          4438910,8 ns   92738,79         64
countParallelN      20          4452791,2 ns   87029,39         64
countParallelN      21          4433738,1 ns   22935,31         64
countParallelN      22          4458825,9 ns   19923,69         64
countParallelN      23          4515860,3 ns   85108,56         64
countParallelN      24          4544477,0 ns   51233,20         64
countParallelN      25          4586049,1 ns   88867,83         64
countParallelN      26          4594524,8 ns  103666,93         64
countParallelN      27          4601314,2 ns   70111,77         64
countParallelN      28          4607962,2 ns   27399,67         64
countParallelN      29          4599412,5 ns   21679,99         64
countParallelN      30          4590896,1 ns   29879,29         64
countParallelN      31          4650310,3 ns  118189,92         64
countParallelN      32          4641270,3 ns   65998,91         64
```

### 3.2

The times decreases up to 8 threads, and the best time is at 8 threads,
which is the same number of cores in the cpu, afterwhich the times plateau but
begin to have decreasing deviation, maybe due to scheduling issues.

### 3.3

```
# OS:   Windows 10; 10.0; amd64
# JVM:  Oracle Corporation; 18.0.2.1
# CPU:  AMD64 Family 23 Model 96 Stepping 1, AuthenticAMD; 16 "cores"
# Date: 2022-10-12T11:35:46+0200
countSequential                12016235,0 ns    2561,15         32
countParallelN       1         14249083,4 ns  925437,93         32
countParallelN       2          8709990,6 ns   36829,62         32
countParallelN       3          5882316,1 ns   36104,65         64
countParallelN       4          4645510,0 ns   31452,04         64
countParallelN       5          4039017,5 ns   35075,69         64
countParallelN       6          3477184,1 ns   43727,77        128
countParallelN       7          3004648,4 ns   54953,38        128
countParallelN       8          2709189,3 ns   40118,30        128
countParallelN       9          3435061,3 ns   44791,63        128
countParallelN      10          3664974,5 ns   76925,91        128
countParallelN      11          3455540,9 ns   42737,83        128
countParallelN      12          3275990,5 ns   25475,96        128
countParallelN      13          3228338,4 ns   25997,21        128
countParallelN      14          3036325,8 ns   37315,45        128
countParallelN      15          3055587,6 ns   57341,61        128
countParallelN      16          3121106,9 ns   73987,23        128
countParallelN      17          3163828,0 ns   19255,08        128
countParallelN      18          3296474,4 ns   58404,86        128
countParallelN      19          3337953,2 ns   80978,31        128
countParallelN      20          3341041,8 ns   36482,43        128
countParallelN      21          3418570,1 ns   81811,53        128
countParallelN      22          3470427,3 ns   93001,65        128
countParallelN      23          3532344,4 ns   60059,33        128
countParallelN      24          3577719,3 ns   47595,54        128
countParallelN      25          3643161,6 ns   56235,35        128
countParallelN      26          3716368,4 ns   78851,29        128
countParallelN      27          3761632,0 ns   84162,84        128
countParallelN      28          3839394,5 ns   56151,47        128
countParallelN      29          4310081,4 ns  247352,19         64
countParallelN      30          4433307,7 ns   41603,12         64
countParallelN      31          4623605,3 ns  124466,82         64
countParallelN      32          5022939,8 ns  112855,50         64
```

Using AtomicLong was faster overall. Generally it is good to use built-in classes, as they are likely to be implemented in a smarter (more effecient) manner, aswell as being well tested. According to this article: https://medium.com/double-pointer/guide-to-atomicinteger-in-java-94c591189fea, Atomic values use a JVM instruction 'compare-and-swap' which allows for thread safe access to a variable, without using locks. Therefor it eliminates the overhead releated to starting and blocking threads.

## 4

```
# OS:   Windows 10; 10.0; amd64
# JVM:  Oracle Corporation; 18.0.2.1
# CPU:  AMD64 Family 23 Model 96 Stepping 1, AuthenticAMD; 16 "cores"
# Date: 2022-10-12T12:13:30+0200
normal                                4,1 ns       0,01   67108864
volatile                              4,7 ns       0,00   67108864
```

Using volatile is slower, because it will always syncronize the variable with main memory, therefor losing out on the more performant caches.

## 5

## 5.1

Yep

## 5.2 and 5.3

```
Array Size: 5697
# Occurences of ipsum :1430
word_search                     9119197,5 ns   28965,73         32
```

## 5.4

```
seq_search                      9204332,8 ns   97586,45         32
par_search1                    12596615,9 ns 1087423,60         32
par_search2                     6066726,9 ns   37811,16         64
par_search3                     4503874,2 ns   43709,15         64
par_search4                     3721517,3 ns   42526,32        128
par_search5                     3533896,3 ns   64043,73        128
par_search6                     3474551,9 ns   36095,22        128
par_search7                     3592682,9 ns   97447,33        128
par_search8                     3682291,3 ns   76628,33        128
par_search9                     3805555,2 ns   82206,76        128
par_search10                    3867247,0 ns   89198,52        128
par_search11                    3879517,4 ns   76501,33        128
par_search12                    3926839,2 ns  179803,40         64
par_search13                    4002402,3 ns  118129,23         64
par_search14                    4064600,9 ns  179939,82         64
par_search15                    4137460,0 ns  156122,20         64
par_search16                    4154485,5 ns   86989,91         64
par_search17                    4228964,2 ns  102348,57         64
par_search18                    4293915,9 ns  108960,28         64
par_search19                    4330671,7 ns  112848,49         64
par_search20                    4354977,5 ns  107621,15         64
par_search21                    4434756,4 ns  173083,26         64
par_search22                    4474026,3 ns  169847,76         64
par_search23                    4503418,4 ns  171929,73         64
par_search24                    4561533,0 ns  164659,38         64
par_search25                    4569627,2 ns  175202,12         64
par_search26                    4616695,2 ns  120729,36         64
par_search27                    4684597,3 ns  108018,70         64
par_search28                    4856356,1 ns  245839,44         64
par_search29                    5573728,1 ns  478203,12         64
par_search30                    4841378,3 ns   76091,81         64
par_search31                    5395785,8 ns  449524,47         64
par_search32                    4568059,4 ns  355763,49         64
```

As in 3.3, we se performance increases in the low thread counts, but at some point
there are so many threads that scheduling becomes a significant issue (seen in growing average), and some threads have to wait for core access longer, some do not (seen in growing normal distribution).