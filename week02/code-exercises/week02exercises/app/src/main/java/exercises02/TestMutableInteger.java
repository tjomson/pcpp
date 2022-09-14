// For week 2
// sestoft@itu.dk * 2014-08-25
// raup@itu.dk * 2021-09-03
package exercises02;

public class TestMutableInteger {
	public static void main(String[] args) {
		final MutableInteger mi = new MutableInteger();
		Thread t = new Thread(() -> {
			var x = 0;
			while (mi.get() == 0) // Loop while zero
			{
				System.out.println(x);
				x++;
				/* Do nothing */ }
			System.out.println("I completed, mi = " + mi.get());
		});
		t.start();
		// try {
		// Thread.sleep(500);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		mi.set(42);
		System.out.println("mi set to 42, waiting for thread ...");
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread t completed, and so does main");
	}
}

class MutableInteger {
	// WARNING: Not ready for usage by concurrent programs
	private int value = 0;

	public synchronized void set(int value) {
		this.value = value;
	}

	public synchronized int get() {
		return value;
	}
}
