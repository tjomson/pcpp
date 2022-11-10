// For week 10
// raup@itu.dk * 2021-10-09
package lecture10;

import java.util.concurrent.atomic.AtomicReference;

class TestLockFreeStack {

    public TestLockFreeStack() {
	LockFreeStack<Integer> lfs = new LockFreeStack<Integer>();
	lfs.push(1);
	lfs.push(42);
	lfs.push(7);
	assert lfs.pop() == 7;
	assert lfs.pop() == 42;
	assert lfs.pop() == 1;
    }

    public static void main(String[] args) {
	new TestLockFreeStack();
    }

    // Treiber's LockFree Stack (Goetz 15.4 & Herlihy 11.2)
    class LockFreeStack<T> {
	AtomicReference<Node<T>> top = new AtomicReference<Node<T>>(); // Initializes to null

	public void push(T value) {
	    Node<T> newHead = new Node<T>(value);
	    Node<T> oldHead;
	    do {
		oldHead      = top.get();
		newHead.next = oldHead;
	    } while (!top.compareAndSet(oldHead,newHead));
	    
	}
	    
	public T pop() {
	    Node<T> newHead;
	    Node<T> oldHead;
	    do {
		oldHead = top.get();
		if(oldHead == null) { return null; }
		newHead = oldHead.next;
	    } while (!top.compareAndSet(oldHead,newHead));	    
	    
	    return oldHead.value;
	}
	
    }
    
    // class for nodes
    private static class Node<T> {
	public final T value;
	public Node<T> next;

	public Node(T value) {
	    this.value = value;
	    this.next  = null;	    
	}
    }
}
