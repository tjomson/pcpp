import java.util.Scanner;
public class SimpleRead {
  public static void main(String[] args) { new SimpleRead(); } 

  public SimpleRead(){ 
    Scanner myObj= new Scanner(System.in);  // Create a Scanner object
    System.out.println(" St(a)rt, St(o)p or (R)eset: ");
    String name= myObj.nextLine();  // Read user input
 
    System.out.println("You entered " + name);
  }
}