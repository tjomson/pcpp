// Stream example lecture week 07
// raup@itu.dk 26/09/2021
// jst@itu.dk 12/10/2022
package lecture07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.concurrent.ThreadLocalRandom;

class Employee {
  int id;
  String dept;
  int salary;
    
  public Employee(int id, String dept, int salary) {
    this.id     = id;
    this.dept   = dept;
    this.salary = salary;
  }
  public int getId() { return this.id; }
  public String getDept() { return this.dept; }
  public int getSalary() { return this.salary; }
}

public class StreamExample {

// Creating an infinite streams of employees
  static private Stream<Employee> randomEmployees() {
    return Stream.generate(
      () -> new Employee(ThreadLocalRandom.current().nextInt(10000),
                  departments[ThreadLocalRandom.current().nextInt(3)],
                  ThreadLocalRandom.current().nextInt(50000) )
    );	
  }

  static String[] departments = {"CS", "DD", "BI"};
  public static void main(String[] args)  { 
    new StreamExample();
  }

  public StreamExample () {
	
    // Convert stream of employees to List
    List<Integer> l = randomEmployees()
        .limit(50)
        .map(Employee::getId)
        .collect(Collectors.toList());

    System.out.println("First: "+l.get(0)+ " Second: "+l.get(1)+" Size: "+l.size()+"\n\n");
/*
    // List of employees per department
    Map<String,List<Employee>> m = randomEmployees()
        .limit(50)
        .collect(Collectors.groupingBy(Employee::getDept));

    // Printing ids of all employees per department
    randomEmployees()
        // .parallel()
        .limit(50)
        // .collect(Collectors.groupingByConcurrent(Employee::getDept))
        .collect(Collectors.groupingByConcurrent(Employee::getDept))
        .forEach((k,v) -> System.out.println(k+": "+v.stream().map(Employee::getId).collect(Collectors.toList()))); 
*/
  }
}


