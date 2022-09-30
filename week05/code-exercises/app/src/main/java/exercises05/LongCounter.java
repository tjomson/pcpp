package exercises05;
class LongCounter {
  private long count = 0;
  public synchronized void increment() {
    count = count + 1;
  }
  public synchronized long get() { 
    return count; 
  }
  public synchronized void add(long c) {
    // to be filled in
  }
  public synchronized void reset() {
    // to be filled in
  }
}