// Week 3
// sestoft@itu.dk * 2015-09-09
package exercises07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class TestWordStream {
  public static void main(String[] args) {
    String filename = "src/main/resources/english-words.txt";
    var str = readWords(filename);
    printStats(str);

    // try {
    // var str = readWordsOnline();
    // System.out.println(str.count());
    // } catch (Exception e) {
    // e.printStackTrace();
    // }

    // Timer t = new Timer();
    // t.play();
    // printPalindromes(str);
    // var time = t.check();
    // System.out.println("seq: " + time);
    // str = readWords(filename);
    // t = new Timer();
    // t.play();
    // printPalindromesPar(str);
    // System.out.println("par: " + time);
    // System.out.println(readWords(filename).count());
  }

  public static Stream<String> readWords(String filename) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(filename));
      return reader.lines();
    } catch (IOException exn) {
      return Stream.<String>empty();
    }
  }

  // 2
  public static void print100(Stream<String> stream) {
    stream.limit(100).forEach(System.out::println);
  }

  // 3
  public static void moreThanOrEqualTo22Letters(Stream<String> stream) {
    stream.filter(x -> x.length() >= 22).forEach(System.out::println);
  }

  // 4
  public static void someWordWithAtLeast22Letters(Stream<String> stream) {
    stream.filter(x -> x.length() >= 22).limit(1).forEach(System.out::println);
  }

  // 5
  public static boolean isPalindrome(String s) {
    int start = 0;
    int end = s.length() - 1;
    while (start < end) {
      if (s.charAt(start) != s.charAt(end))
        return false;
      start++;
      end--;
    }
    return true;
  }

  public static void printPalindromes(Stream<String> stream) {
    stream.filter(TestWordStream::isPalindrome).forEach(System.out::println);
  }

  // 6
  public static void printPalindromesPar(Stream<String> stream) {
    stream.parallel().filter(TestWordStream::isPalindrome).forEach(System.out::println);
  }

  // 7
  public static Stream<String> readWordsOnline() throws Exception {
    var conn = (HttpURLConnection) new URL("https://staunstrups.dk/jst/english-words.txt").openConnection();
    var reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    return reader.lines();
  }

  // 8

  public static void printStats(Stream<String> stream) {
    var stats = stream.map(x -> x.length()).collect(Collectors.summarizingInt(Integer::intValue));
    System.out.println(stats.getMin() + " " + stats.getMax() + " " + stats.getAverage());
  }

  // public static void minWordLength(Stream<String> stream) {
  // System.out.println(stream.map(x -> x.length()).min((i, j) ->
  // i.compareTo(j)).get());
  // }

  // public static void maxWordLength(Stream<String> stream) {
  // System.out.println(stream.map(x -> x.length()).max((i, j) ->
  // i.compareTo(j)).get());
  // }

  // public static void avgWordLength(Stream<String> stream) {
  // var str = stream.map(String::length);
  // str.max((i, j) -> i.compareTo(j));
  // var count = str.count();
  // double sum = str.reduce(0, (acc, ele) -> acc + ele) * 1.0;
  // System.out.println(sum / count);
  // }

  public static Map<Character, Integer> letters(String s) {
    Map<Character, Integer> res = new TreeMap<>();
    // TO DO: Implement properly
    return res;
  }
}
