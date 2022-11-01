package lecture09;

import java.awt.event.*;  
import javax.swing.*; 

// Java Swing Button
// jst@itu.dk Updated October 30, 2022

class swingButton {
  public static void main(String[] args) { new swingButton(); } 

  final private static JFrame f= new JFrame("Button Demo"); 
  final private JButton startButton= new JButton("Start");	
  
  public swingButton(){
    f.setBounds(0, 0, 200, 120); 
    f.setLayout(null);  
    f.setVisible(true);

    startButton.setBounds(20, 20, 140, 30); 
    startButton.addActionListener(new ActionListener(){  
      public void actionPerformed(ActionEvent e){  
        System.out.println("Start pushed");  
      }  
    });  

    // set up user interface
    f.add(startButton);  
  }		
}