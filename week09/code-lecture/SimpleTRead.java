import java.util.Scanner;
public class SimpleTRead {
  public static void main(String[] args) { new SimpleTRead(); } 

  public SimpleTRead(){ 
    new Button().start();
    //Continue working
    System.out.print("-");
  }

  public class Button extends Thread {
    public void run() {	    
	    Scanner myObj= new Scanner(System.in);  // Create a Scanner object
      System.out.println(" St(a)rt, St(o)p or (R)eset: ");
      String name= myObj.nextLine();  // Read user input
 
      System.out.println("You entered " + name);
    } 
  }
}