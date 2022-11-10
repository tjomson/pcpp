package exercises09;

import java.awt.event.*;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
/* This example is inspired by the StopWatch app in Head First. Android Development
   http://shop.oreilly.com/product/0636920029045.do
   Modified to Java, October 7, 2021 by Jørgen Staunstrup, ITU, jst@itu.dk
   Updated October 30, 2022*/

public class StopwatchN {
    public static void main(String[] args) {
        new StopwatchN(8);
    }

    final static JFrame f = new JFrame("Stopwatch");
    private ArrayList<stopwatchUI> uis = new ArrayList<>();

    // Setting up the three streams for the Buttons and the display

    public StopwatchN(int n) {
        f.setBounds(0, 0, 220 * n, 220);
        f.setLayout(null);
        for (int i = 0; i < n; i++) {
            uis.add(new stopwatchUI(220 * (i), f));
        }
        f.setVisible(true);

        for (var ui : uis) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            TimeUnit.MILLISECONDS.sleep(100);
                            ui.updateTime();
                        }
                    } catch (java.lang.InterruptedException e) {
                        System.out.println(e.toString());
                    }
                }
            }.start();
        }
    }
}
