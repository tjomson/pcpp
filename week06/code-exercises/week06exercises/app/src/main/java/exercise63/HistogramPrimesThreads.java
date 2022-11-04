package exercise63;

// first version by Kasper modified by jst@itu.dk 24-09-2021
// raup@itu.dk * 05/10/2022

public class HistogramPrimesThreads {
	public static void main(String[] args) {
		new HistogramPrimesThreads();
	}

	public HistogramPrimesThreads() {

		// TODO: Replace below with an instance of Histogram2 exercise 6.3.1 (recall
		// that Histogram1 is not thread-safe)
		final Histogram histogram = new Histogram2(25); // 25 bins sufficient for a range of 0..4_999_999

		// TODO: Run it using multiple threads, and check the countFactors function
		// below (it might be useful)

		test(histogram, 25);

		// Finally we plot the result to ensure that it looks as expected (see example
		// output in the exercise script)
		dump(histogram);
	}

	public static Histogram test(Histogram histogram, int thread_count) {
		final int upTo = 5000000;

		final int workPrThread = upTo / thread_count;
		final Thread[] threads = new Thread[thread_count];

		for (int i = 0; i < thread_count; i++) {
			final int index = i;
			final int to = ((i * workPrThread) + workPrThread > upTo) ? upTo - (i * workPrThread) : workPrThread;
			threads[i] = new Thread(() -> {
				for (int x = 0; x < to; x++) {
					histogram.increment(countFactors((index * workPrThread) + x));
				}
			});
		}

		for (Thread thread : threads) {
			thread.start();
		}
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
			}
		}

		return histogram;
	}

	// Returns the number of prime factors of `p`
	public static int countFactors(int p) {
		if (p < 2)
			return 0;
		int factorCount = 1, k = 2;
		while (p >= k * k) {
			if (p % k == 0) {
				factorCount++;
				p = p / k;
			} else
				k = k + 1;
		}
		return factorCount;
	}

	public static void dump(Histogram histogram) {
		for (int bin = 0; bin < histogram.getSpan(); bin = bin + 1) {
			System.out.printf("%4d: %9d%n", bin, histogram.getCount(bin));
		}
		System.out.printf("      %9d%n", histogram.getTotal());
	}
}
