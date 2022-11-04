package lecture07;
// Simple Lambda example
// jst@itu.dk * 2022-09-12 Simple example to illustrate use of Lambda

import java.util.function.Function;

class LambdaExample {
  public static void main(String[] args) { new LambdaExample(); }
  
  public LambdaExample() {
    System.out.println("I: "+increment(f));
  }

  Function<Integer, Integer> f = x -> x+1;

  private static int increment(Function<Integer, Integer> add1) { return add1.apply(2);}
}