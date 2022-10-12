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
# OS:   Linux; 5.15.0-48-generic; amd64
# JVM:  Private Build; 18.0.2-ea
# CPU:  null; 8 "cores"
# Date: 2022-10-10T16:06:44+0200
countSequential                 9210118.0 ns  355072.46         32
countParallelN       1          8938547.7 ns  295345.64         32
countParallelN       2          5617859.8 ns   53996.10         64
countParallelN       3          4177278.9 ns   52888.21         64
countParallelN       4          3357343.0 ns   76241.64        128
countParallelN       5          3537545.8 ns   31142.39        128
countParallelN       6          3138139.1 ns   15199.82        128
countParallelN       7          2742315.8 ns   81603.30        128
countParallelN       8          2672001.6 ns  267831.23        128
countParallelN       9          3577634.4 ns   37047.87        128
countParallelN      10          3402494.4 ns   50722.95        128
countParallelN      11          3198427.7 ns   81320.48        128
countParallelN      12          3159077.1 ns   90476.66        128
countParallelN      13          3146109.4 ns   35896.83        128
countParallelN      14          3024722.7 ns   94347.27        128
countParallelN      15          2865315.0 ns   67070.09        128
countParallelN      16          2799071.7 ns  100554.09        128
countParallelN      17          3161209.6 ns   88385.01        128
countParallelN      18          3246833.2 ns   64989.23        128
countParallelN      19          3194721.1 ns   89140.03        128
countParallelN      20          3123737.9 ns   22242.49        128
countParallelN      21          3142784.8 ns  118334.25        128
countParallelN      22          3214736.8 ns  149945.28        128
countParallelN      23          3156614.4 ns  129732.54        128
countParallelN      24          3125756.5 ns  115182.51        128
countParallelN      25          3285936.4 ns  238705.92        128
countParallelN      26          3125654.3 ns  110644.44        128
countParallelN      27          3468242.7 ns  694558.62        128
countParallelN      28          3225398.2 ns   45055.10        128
countParallelN      29          3303579.6 ns  135983.22        128
countParallelN      30          3342409.0 ns  113686.18        128
countParallelN      31          3312307.7 ns  137185.50        128
countParallelN      32          3333199.9 ns  130182.62        128
```

### 3.2

The times decreases up to 8 threads, and the best time is at 8 threads,
which is the same number of cores in the cpu, afterwhich the times plateau but
begin to have decreasing deviation, maybe due to scheduling issues.
