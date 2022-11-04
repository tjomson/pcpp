package exercises09;

import java.awt.event.*;
import javax.swing.*;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
/* This example is inspired by the StopWatch app in Head First. Android Development
   http://shop.oreilly.com/product/0636920029045.do
   Modified to Java, October 7, 2021 by Jørgen Staunstrup, ITU, jst@itu.dk
   Updated October 30, 2022*/

public class Stopwatch2 {
    public static void main(String[] args) {
        new Stopwatch2();
    }

    final static JFrame f = new JFrame("Stopwatch");

    // Setting up the three streams for the Buttons and the display
    final static stopwatchUI myUI = new stopwatchUI(0, f);
    final static stopwatchUI myUI2 = new stopwatchUI(250, f);

    public Stopwatch2() {
        f.setBounds(0, 0, 520, 220);
        f.setLayout(null);
        f.setVisible(true);

        // Background Thread simulating a clock ticking every 1 second
        new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        TimeUnit.MILLISECONDS.sleep(100);
                        myUI.updateTime();
                    }
                } catch (java.lang.InterruptedException e) {
                    System.out.println(e.toString());
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        TimeUnit.MILLISECONDS.sleep(100);
                        myUI2.updateTime();
                    }
                } catch (java.lang.InterruptedException e) {
                    System.out.println(e.toString());
                }
            }
        }.start();
    }
}
