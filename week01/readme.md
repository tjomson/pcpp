# Lecture 1: Intro to concurrency and mutual exclusion problem

## Goals

The goals of this lecture are:

* Being able to distinguish the 3 main uses of concurrency
* Being able to detect race conditions and identify critical sections
* Understanding the details of mutual exclusion
* Use locks to ensure mutual exclusion

## Readings 

* Goetz:
  * Chapter 1, complete | 11 pages
  * Chapter 2, complete except Section 2.5 | 14 pages
  * Chapter 16, Section 16.1.3 | 4 pages
  
* Herlihy:
  * Chapter 1, Sections 1.1 to 1.2 | 10 pages
  * Chapter 2, Sections 2.1 to 2.4, and 2.6 (omit the details of proofs) | 11 pages
  * Chapter 7, Section 7.1 | 4 pages

* Staunstrup & Pardo
  * [Concurrency note](concurrencyNotes/concurrencyPCPP.pdf)
  
### Optional readings

* Dijkstra's paper introducing (and solving) the mutual exclusion problem.
  * E. W. Dijkstra. [Solution of a Problem in Concurrent  Programming  Control](https://dl-acm-org.ep.ituproxy.kb.dk/doi/pdf/10.1145/365559.365617). Communications of the ACM. Volume 8. Issue 9. 1965.

* Lamport's paper introducing the _happened-before_ relation.
  * Leslie Lamport. [Time, Clocks, and the Ordering of Events in a Distributed System](https://www.microsoft.com/en-us/research/uploads/prod/2016/12/Time-Clocks-and-the-Ordering-of-Events-in-a-Distributed-System.pdf). Communications of the ACM. Volume 21. Issue 7. 1978.
  
* The proofs in Sections 2.1, 2.3 and 2.6 in Herlihy.

## Exercises

See file [exercises01.pdf](exercises01.pdf).

## Lecture slides

To appear.
