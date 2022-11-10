package exercises09;

import java.awt.event.*;
import javax.swing.*;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.Locale;
import java.util.concurrent.TimeUnit;
// User interface for Stopwatch, October 7, 2021 by Jørgen Staunstrup, ITU, jst@itu.dk
// Updated October 30, 2022

class StopwatchUIObservable {
    private static JFrame lf;
    final private JButton startButton = new JButton("Start");
    final private JButton stopButton = new JButton("Stop");
    final private JButton resetButton = new JButton("Reset");
    final private JTextField tf = new JTextField();

    final private String allzero = "0:00:00:0";
    private SecCounter lC = new SecCounter(0, false, tf);

    public synchronized void updateTime() {
        int desiSeconds = lC.incr();
        int seconds = desiSeconds / 10;
        // Potentila race condition !!!
        if (seconds >= 0) {
            int hours = seconds / 3600;
            int minutes = (seconds % 3600) / 60;
            int secs = seconds % 60;
            int desis = desiSeconds % 10;
            String time = String.format(Locale.getDefault(), "%d:%02d:%02d:%d", hours, minutes, secs, desis);
            tf.setText(time);
        }
    };

    final Observable<Integer> rxStart = Observable.create(new ObservableOnSubscribe<Integer>() {
        @Override
        public void subscribe(ObservableEmitter<Integer> e) throws Exception {
            startButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ee) {
                    lC.setRunning(true);
                }
            });
        }
    });

    final Observable<Integer> rxStop = Observable.create(new ObservableOnSubscribe<Integer>() {
        @Override
        public void subscribe(ObservableEmitter<Integer> e) throws Exception {
            stopButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ee) {
                    lC.setRunning(false);
                }
            });
        }
    });

    final Observable<Integer> rxReset = Observable.create(new ObservableOnSubscribe<Integer>() {
        @Override
        public void subscribe(ObservableEmitter<Integer> e) throws Exception {
            resetButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ee) {
                    lC.reset();
                }
            });
        }
    });

    public StopwatchUIObservable(int x, JFrame jF) {
        int lx = x + 50;
        lf = jF;
        tf.setBounds(lx, 10, 60, 20);
        tf.setText(allzero);

        startButton.setBounds(lx, 50, 95, 25);
        rxStart.subscribe(display);

        stopButton.setBounds(lx, 90, 95, 25);
        rxStop.subscribe(display);

        resetButton.setBounds(lx, 130, 95, 25);
        rxReset.subscribe(display);

        // set up user interface
        lf.add(tf);
        lf.add(startButton);
        lf.add(stopButton);
        lf.add(resetButton);
    }

    final Observer<Integer> display = new Observer<Integer>() {
        @Override
        public void onSubscribe(Disposable d) {
        }

        @Override
        public void onNext(Integer value) {
            updateTime();
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("onError: ");
        }

        @Override
        public void onComplete() {
            System.out.println("onComplete: All Done!");
        }
    };
}
