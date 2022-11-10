# Week 9 solutions

## 9.1

### 1

See Stopwatch.java line 33 and stopwatchUI.java 21-29

### 2

If there are multiple threads, once might update the value while another is updating the UI, so they are not synced. Added synchronzed to updateTime(), stopwatchUI.java line 20

### 3

See Stopwatch2.java

### 4

See StopwatchN.java

## 9.2

### 1

Output:

```
Hello World!

Deprecated Gradle features were used in this build, making it incompatible with Gradle 8.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

See https://docs.gradle.org/7.5.1/userguide/command_line_interface.html#sec:command_line_warnings
```

### 2

Output:

```
Hello World

Deprecated Gradle features were used in this build, making it incompatible with Gradle 8.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

See https://docs.gradle.org/7.5.1/userguide/command_line_interface.html#sec:command_line_warnings
```

### 3

Output is slightly different from the expected:

```
Processing Thread pool-1-thread-1
Processing Thread pool-3-thread-1
Receiver Thread pool-1-thread-1, Item length 1
Receiver Thread pool-1-thread-1, Item length 2
Processing Thread pool-4-thread-1
Receiver Thread pool-4-thread-1, Item length 3
```

The three strings are mapped to get the length of the string. On the next update, it will print the thread name. It adds three observers to this observable.
It then subscribes to each observer, and prints the length of each string

## 9.3

### 1

See StopwatchEx.java line 31

### 2
