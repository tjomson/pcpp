# Solutions for week12

## 12.1
- WorkerIds exists to ensure that workers are getting unique ids associsated to them.
- idleWorkers keeps track of works that are not actively computing. Using a queue makes it so that given infinite tasks, each worker will do work at some point.
- busyWorkers keeps track of works that are actively computing. This enables a check to make sure that the server does not add workers, that it did not create to the idle queue.
- tasks keeps track of each task not being worked on, an which client it was given by. Using a queue makes it so that older tasks are prioritized.


## 12.2
- curWorkers keeps track of how many works the server has, using this we make make sure not to create more than allowed.
- maxWorkers sets the limit on the worker amount.

A minWorkers field is not really needed, except for in the constructor to initialize the correct amount of workers.

## 12.3
The behavior runs through each task in the message and handles the cases as such:
### a
If the idleWorkers queue is not empty, i.e. there are available workers, a worker is dequeued from the list, added to the busyWorkers set, and is given the task to compute.
### b
If there were no idle workers, and the number of current workers is less than the maximum allowed, the number of currect workers is incremented, a new worker is spawned and added to the busyWorkers set, and is given the task to compute.
### c
If there were no idle workers, and the maximum number of workers was reached, the task is enqueued in the task queue.

## 12.4
At any place in the code were new workers are spawn, they are also added to the "watchlist" of the server. That is, in the constructor, case 'b' of the onComputeTasks behavior, and in the onCrach behavior.

The onCrash behavior is executed on the ChildFailed signal, and quite simply spawns a new worker, adding it to the idle queue.

## 12.5
If the worker reporting that it is done computing, is not registered in the busyWorkers set, it is ignored.
### a
If the tasks queue is not empty, i.e. there is pending work, the worker reporting that it is done computing, is given the next task in the queue.
### b
If there was no pending work, the worker is removed from the busyWorkers set, and is added to the idleWorkers queue.